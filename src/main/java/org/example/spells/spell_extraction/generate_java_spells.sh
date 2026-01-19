#!/bin/bash

# Générateur de classes Java basé sur un parseur CSV robuste
# Utilisation: ./generate_java_spells.sh spells_export.csv common.csv output_dir

CSV_FILE="${1:-spells_export.csv}"
COMMON_FILE="${2:-common.csv}"
OUTPUT_DIR="${3:-./spells_java}"

if [ ! -f "$CSV_FILE" ]; then
    echo "❌ Fichier CSV des sorts non trouvé: $CSV_FILE"
    exit 1
fi

if [ ! -f "$COMMON_FILE" ]; then
    echo "❌ Fichier common.csv non trouvé: $COMMON_FILE"
    exit 1
fi

mkdir -p "$OUTPUT_DIR"
echo "🔨 Génération des classes Java..."
echo "📚 Fichier de traductions: $COMMON_FILE"
echo "📁 Dossier de sortie: $OUTPUT_DIR"
echo ""

# Fonction pour échapper les caractères spéciaux pour Java
escape_java_string() {
    local value="$1"
    # Échapper les guillemets, retours à la ligne, etc.
    value="${value//\\/\\\\}"  # Backslash
    value="${value//\"/\\\"}"  # Guillemets
    value="${value//$'\n'/\\n}"  # Retours à la ligne
    value="${value//$'\r'/\\r}"  # Retours chariot
    value="${value//$'\t'/\\t}"  # Tabulations
    echo "$value"
}

# Fonction pour chercher une traduction dans common.csv
get_translation() {
    local key="$1"
    local file="$2"
    
    awk -v search_key="$key" '
    BEGIN { FS = "," }
    NR == 1 { 
        # Parser le header pour trouver l''index de la colonne "en"
        in_quotes = 0
        field_num = 0
        field_value = ""
        
        for (i = 1; i <= length($0); i++) {
            char = substr($0, i, 1)
            
            if (char == "\"") {
                in_quotes = !in_quotes
            } else if (char == "," && !in_quotes) {
                field_num++
                fields[field_num] = field_value
                field_value = ""
            } else {
                field_value = field_value char
            }
        }
        field_num++
        fields[field_num] = field_value
        
        # Trouver l''index de la colonne English
        for (i = 1; i <= field_num; i++) {
            col = fields[i]
            gsub(/"/, "", col)
            if (col == "en") {
                english_col = i
                break
            }
        }
        next
    }
    
    {
        # Parser chaque ligne en respectant les guillemets
        in_quotes = 0
        field_num = 0
        field_value = ""
        
        for (i = 1; i <= length($0); i++) {
            char = substr($0, i, 1)
            
            if (char == "\"") {
                in_quotes = !in_quotes
            } else if (char == "," && !in_quotes) {
                field_num++
                fields[field_num] = field_value
                field_value = ""
            } else {
                field_value = field_value char
            }
        }
        field_num++
        fields[field_num] = field_value
        
        # Extraire la clé et la traduction
        key = fields[1]
        gsub(/"/, "", key)
        
        if (key == search_key && english_col > 0) {
            translation = fields[english_col]
            gsub(/"/, "", translation)
            print translation
            exit
        }
    }
    ' "$file"
}

# Fonction pour convertir le type Noita en type Java
convert_spell_type() {
    local type="$1"
    case "$type" in
        *PROJECTILE*) echo "projectile" ;;
        *STATIC*) echo "static_projectile" ;;
        *MODIFIER*) echo "modifier" ;;
        *DRAW_MANY*) echo "multicast" ;;
        *PASSIF*) echo "passif" ;;
        *UTILITY*) echo "utility" ;;
        *MATERIAL*) echo "material" ;;
        *) echo "other" ;;
    esac
}

# Fonction pour mapper spawn_level et spawn_probability aux indices
build_spawn_probabilities() {
    local levels="$1"
    local probs="$2"
    
    # Créer un tableau de 11 zéros
    local spawn_array=(0 0 0 0 0 0 0 0 0 0 0)
    
    # Parser les listes
    IFS=',' read -ra level_array <<< "$levels"
    IFS=',' read -ra prob_array <<< "$probs"
    
    # Mapper chaque niveau à sa probabilité
    for i in "${!level_array[@]}"; do
        local level=$(echo "${level_array[$i]}" | xargs)
        local prob=$(echo "${prob_array[$i]}" | xargs)
        
        # Vérifier que level est un nombre valide entre 0 et 10
        if [[ "$level" =~ ^[0-9]+$ ]] && [ "$level" -ge 0 ] && [ "$level" -le 10 ]; then
            spawn_array[$level]="$prob"
        fi
    done
    
    # Construire la chaîne
    local result="${spawn_array[0]}"
    for i in {1..10}; do
        result="${result}, ${spawn_array[$i]}"
    done
    
    echo "$result"
}

count=0

# Parser le CSV avec awk
awk '
BEGIN { FS = "," }
NR == 1 { next }  # Skip header

{
    # Parser les champs en respectant les guillemets
    in_quotes = 0
    field_num = 0
    field_value = ""
    
    for (i = 1; i <= length($0); i++) {
        char = substr($0, i, 1)
        
        if (char == "\"") {
            in_quotes = !in_quotes
        } else if (char == "," && !in_quotes) {
            field_num++
            fields[field_num] = field_value
            field_value = ""
        } else {
            field_value = field_value char
        }
    }
    field_num++
    fields[field_num] = field_value
    
    # Extraire les données
    id = fields[1]
    name_key = fields[2]
    desc_key = fields[3]
    type_field = fields[4]
    price = fields[5]
    mana = fields[6]
    max_uses = fields[7]
    spawn_level = fields[8]
    spawn_prob = fields[9]
    sprite_path = fields[10]
    
    # Nettoyer les guillemets et espaces
    gsub(/"/, "", id)
    gsub(/"/, "", name_key)
    gsub(/"/, "", desc_key)
    gsub(/"/, "", type_field)
    gsub(/"/, "", price)
    gsub(/"/, "", mana)
    gsub(/"/, "", max_uses)
    gsub(/"/, "", spawn_level)
    gsub(/"/, "", spawn_prob)
    gsub(/"/, "", sprite_path)
    
    # Ignorer les lignes vides
    if (id == "") next
    
    # Afficher pour traitement par bash
    print id "|" name_key "|" desc_key "|" type_field "|" price "|" mana "|" max_uses "|" spawn_level "|" spawn_prob "|" sprite_path
}
' "$CSV_FILE" | while IFS='|' read -r id name_key desc_key type_field price mana max_uses spawn_level spawn_prob sprite_path; do
    
    count=$((count + 1))
    
    # Extraire le nom du fichier sprite
    sprite_file=$(basename "$sprite_path")
    
    # Obtenir les traductions
    name=$(get_translation "${name_key#\$}" "$COMMON_FILE")
    if [ -z "$name" ]; then
        name=$(echo "$id" | sed 's/_/ /g' | sed 's/\b\(.\)/\U\1/g')
    fi
    
    description=$(get_translation "${desc_key#\$}" "$COMMON_FILE")
    if [ -z "$description" ]; then
        description="$name"
    fi
    
    # Convertir le type Noita en type Java
    spell_type=$(convert_spell_type "$type_field")
    
    # Échapper les chaînes pour Java
    name_escaped=$(escape_java_string "$name")
    description_escaped=$(escape_java_string "$description")
    
    # Construire les probabilités de spawn
    spawn_probs=$(build_spawn_probabilities "$spawn_level" "$spawn_prob")
    
    # Vérifier les charges
    has_charges="false"
    if [ ! -z "$max_uses" ] && [ "$max_uses" -gt 1 ] 2>/dev/null; then
        has_charges="true"
    fi
    
    # Défauts
    price=${price:-0}
    mana=${mana:-0}
    
    # Générer le fichier Java
    cat > "${OUTPUT_DIR}/${id}.java" << EOF
package org.example.spells;

import org.example.main.*;

public class ${id} extends Spell{
    @Override
    protected void initialization(){
        this.name = "${name_escaped}";
        //this.alias = new String[]{this.getClass().getSimpleName(), this.name};
        this.imageFile = "${sprite_file}";
        //this.emote = "";
        this.description = "${description_escaped}";
        this.type = SpellType.${spell_type};
        this.spawnProbabilities = new SpawnProbabilities(${spawn_probs});
        this.price = ${price};
        this.manaCost = ${mana};
EOF
    
    if [ "$has_charges" = "true" ]; then
        cat >> "${OUTPUT_DIR}/${id}.java" << EOF
        this.hasCharges = true;
        this.maxCharges = ${max_uses};
EOF
    fi
    
    cat >> "${OUTPUT_DIR}/${id}.java" << 'EOF'
    }

    @Override
    public void action(CardPool cardPool, CastState castState, int recursionLevel, int iterationLevel){
        // TODO: Implement action logic
    }
}
EOF
    
    echo "✓ [$count] ${id}.java"
    
done

echo ""
echo "=================================================="
echo "✅ Génération terminée!"
file_count=$(ls -1 "${OUTPUT_DIR}"/*.java 2>/dev/null | wc -l)
echo "📄 Fichiers Java générés: $file_count"
echo "📍 Localisation: $OUTPUT_DIR"