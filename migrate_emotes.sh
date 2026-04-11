#!/bin/bash

# Usage: ./migrate_emotes.sh <dossier> <chemin/emotes.properties>
# Ex:    ./migrate_emotes.sh src/main/java/org/example/projectiles src/main/resources/emotes.properties

FOLDER=${1:-.}
EMOTES_FILE=${2:-src/main/resources/emotes.properties}

# Créer le fichier s'il n'existe pas
touch "$EMOTES_FILE"

echo "🔍 Scan de $FOLDER..."

# Parcourir tous les .java du dossier
find "$FOLDER" -name "*.java" | while read -r file; do

    # Trouver tous les emotes <:name:id> dans le fichier
    grep -oP '<:\K[^:]+:[0-9]+(?=>)' "$file" | while IFS=: read -r name id; do

        # Ajouter dans emotes.properties si pas déjà présent
        if ! grep -q "^$name=" "$EMOTES_FILE"; then
            echo "$name=$id" >> "$EMOTES_FILE"
            echo "  ✅ Ajouté : $name=$id"
        fi

        # Remplacer dans le fichier Java
        # "<:acidshot:1447276635329134692>" → EmoteConfig.get("acidshot")
        sed -i "s|\"<:$name:$id>\"|getEmoteConfig(\"$name\")|g" "$file"
        echo "  🔄 Remplacé dans $file : $name"

    done
done

echo ""
echo "✅ Migration terminée !"
echo "📄 emotes.properties :"
cat "$EMOTES_FILE"