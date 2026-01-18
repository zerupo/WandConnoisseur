#!/bin/bash

# Script de copie des sprites Noita depuis un CSV vers un dossier filtré
# Parse correctement le CSV en respectant les guillemets

CSV_FILE="${1:-spells_export.csv}"
IMAGES_DIR="${2:-.}"
OUTPUT_DIR="filtered"

# Vérifications
if [ ! -f "$CSV_FILE" ]; then
    echo "❌ Fichier CSV non trouvé: $CSV_FILE"
    exit 1
fi

if [ ! -d "$IMAGES_DIR" ]; then
    echo "❌ Dossier d'images non trouvé: $IMAGES_DIR"
    exit 1
fi

# Créer le dossier de sortie
mkdir -p "$OUTPUT_DIR"
echo "📁 Dossier de sortie créé: $OUTPUT_DIR"
echo "📂 Dossier source: $IMAGES_DIR"
echo ""

echo "🔍 Indexation des fichiers PNG..."
find "$IMAGES_DIR" -name "*.png" -type f > /tmp/png_index.txt 2>/dev/null
png_count=$(wc -l < /tmp/png_index.txt)
echo "✓ $png_count fichiers PNG trouvés"
echo ""

echo "🔍 Traitement du CSV avec parsing robuste..."
echo ""

# Utiliser awk pour parser correctement le CSV en respectant les guillemets
awk -F'"' '
NR == 1 { next }  # Skip header

{
    # Strategy: les guillemets délimitent les champs CSV
    # Compter les guillemets pour trouver les bons champs
    
    # Le format est: ID,"Name",...,"Sprite",...
    # Champs non quotés sont séparés par des virgules
    # Champs quotés sont entre guillemets
    
    # Extraire l''ID (premier champ non quoté)
    id = $1
    gsub(/^,/, "", id)  # Enlever virgule au début si présente
    
    # Chercher le sprite: c''est un champ qui contient "data/ui_gfx/gun_actions"
    # et qui finit par .png (mais pas _unidentified.png)
    
    sprite_path = ""
    
    # Parcourir tous les champs entre guillemets (les champs pairs dans le split)
    for (i = 2; i <= NF; i += 2) {
        path = $i
        
        # Chercher un chemin valide de sprite
        if (path ~ /^data\/ui_gfx\/gun_actions\/.*\.png$/ && 
            path !~ /_unidentified\.png$/) {
            sprite_path = path
            break
        }
    }
    
    # Si on a trouvé un sprite valide, l''ajouter à la sortie
    if (sprite_path != "") {
        # Extraire le nom du fichier
        n = split(sprite_path, parts, "/")
        filename = parts[n]
        
        print id "|" filename "|" sprite_path
    }
}
' "$CSV_FILE" > /tmp/sprites_to_copy.txt

# Copier chaque sprite
copied=0
not_found=0
total=0

while IFS='|' read -r id filename sprite_path; do
    if [ ! -z "$filename" ]; then
        total=$((total + 1))
        
        # Chercher le fichier dans l''index
        found_path=$(grep "/$filename$" /tmp/png_index.txt | head -1)
        
        if [ ! -z "$found_path" ]; then
            dest="$OUTPUT_DIR/$filename"
            
            # Copier si n''existe pas déjà
            if [ ! -f "$dest" ]; then
                if cp "$found_path" "$dest" 2>/dev/null; then
                    echo "✓ [$id] $filename"
                    copied=$((copied + 1))
                else
                    echo "✗ [$id] $filename (erreur copie)"
                fi
            fi
        else
            echo "✗ [$id] $filename (non trouvé dans $IMAGES_DIR)"
            not_found=$((not_found + 1))
        fi
    fi
done < /tmp/sprites_to_copy.txt

# Afficher les statistiques finales
echo ""
echo "=================================================="
echo "📊 Statistiques finales:"
echo "   • Sprites traités: $total"
echo "   • Sprites copiés: $copied"
echo "   • Sprites non trouvés: $not_found"
echo ""

file_count=$(find "$OUTPUT_DIR" -name "*.png" -type f 2>/dev/null | wc -l)
echo "📁 Fichiers PNG dans '$OUTPUT_DIR': $file_count"
echo ""
echo "✅ Opération terminée!"

# Nettoyage
rm -f /tmp/png_index.txt /tmp/sprites_to_copy.txt