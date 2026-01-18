#!/bin/bash

# Ajoute le code Lua original en commentaire dans les fichiers Java
# Utilisation: ./add_lua_comments.sh gun_actions.lua spells_java/

LUA_FILE="${1:-./gun_actions.lua}"
JAVA_DIR="${2:-./spells_java}"

if [ ! -f "$LUA_FILE" ]; then
    echo "❌ Fichier Lua non trouvé: $LUA_FILE"
    exit 1
fi

if [ ! -d "$JAVA_DIR" ]; then
    echo "❌ Dossier Java non trouvé: $JAVA_DIR"
    exit 1
fi

echo "📝 Ajout des commentaires Lua..."
echo ""

count=0

# Parcourir tous les fichiers Java
for java_file in "$JAVA_DIR"/*.java; do
    java_filename=$(basename "$java_file")
    spell_id="${java_filename%.java}"
    
    # Extraire le code Lua original pour ce sort
    lua_content=$(awk -v id="$spell_id" '
    BEGIN { 
        in_spell = 0
        found_id = 0
    }
    /^[[:space:]]*\{[[:space:]]*$/ {
        if (!in_spell) {
            in_spell = 1
            spell_buffer = $0 "\n"
        }
        next
    }
    in_spell {
        spell_buffer = spell_buffer $0 "\n"
    }
    in_spell && /id[[:space:]]*=[[:space:]]*"'"$spell_id"'"/ {
        found_id = 1
    }
    /^[[:space:]]*\},[[:space:]]*$/ || /^[[:space:]]*\}\},[[:space:]]*$/ {
        if (in_spell && found_id) {
            print spell_buffer $0
            exit
        }
        if (in_spell) {
            in_spell = 0
            found_id = 0
            spell_buffer = ""
        }
    }
    ' "$LUA_FILE" 2>/dev/null)
    
    # Si on a trouvé du contenu Lua
    if [ ! -z "$lua_content" ]; then
        # Créer un fichier temporaire
        temp_file="${java_file}.tmp"
        
        # Copier le fichier Java jusqu'à la dernière accolade
        head -n -1 "$java_file" > "$temp_file"
        
        # Ajouter le commentaire Lua
        cat >> "$temp_file" << 'EOF'

/*
=== CODE LUA ORIGINAL (gun_actions.lua) ===
EOF
        
        echo "$lua_content" | sed 's/^//' >> "$temp_file"
        
        cat >> "$temp_file" << 'EOF'
*/
}
EOF
        
        # Remplacer le fichier original
        mv "$temp_file" "$java_file"
        
        count=$((count + 1))
        echo "✓ [$count] $java_filename (Lua comment added)"
    else
        echo "⊘ [$count] $java_filename (Lua not found)"
    fi
done

echo ""
echo "=================================================="
echo "✅ Ajout des commentaires terminé!"
echo "📄 Fichiers mis à jour: $count"