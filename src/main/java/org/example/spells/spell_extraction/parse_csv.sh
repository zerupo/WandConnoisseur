#!/bin/bash

# Parseur CSV robuste pour extraire les données des sorts Noita
# Utilisation: ./parse_csv.sh spells_export.csv

CSV_FILE="${1:-spells_export.csv}"

if [ ! -f "$CSV_FILE" ]; then
    echo "❌ Fichier CSV non trouvé: $CSV_FILE"
    exit 1
fi

# Créer un script awk qui parse correctement le CSV en respectant les guillemets
awk '
BEGIN {
    FS = ","
}

NR == 1 {
    # Parser le header pour identifier les colonnes
    for (i = 1; i <= NF; i++) {
        col = $i
        gsub(/"/, "", col)
        col_name[i] = col
    }
    
    # Afficher les colonnes trouvées
    print "# Colonnes trouvées:" > "/dev/stderr"
    for (i = 1; i <= NF; i++) {
        printf "%2d: %s\n", i, col_name[i] > "/dev/stderr"
    }
    next
}

{
    # Parser chaque ligne correctement en respectant les guillemets
    
    # Variables pour gérer les guillemets
    in_quotes = 0
    field_num = 0
    field_value = ""
    
    for (i = 1; i <= length($0); i++) {
        char = substr($0, i, 1)
        
        if (char == "\"") {
            in_quotes = !in_quotes
        } else if (char == "," && !in_quotes) {
            # Fin de champ
            field_num++
            fields[field_num] = field_value
            field_value = ""
        } else {
            field_value = field_value char
        }
    }
    
    # Dernier champ
    field_num++
    fields[field_num] = field_value
    
    # Extraire les valeurs importantes
    id = fields[1]
    name = fields[2]
    description = fields[3]
    type_field = fields[4]
    price = fields[5]
    mana = fields[6]
    max_uses = fields[7]
    spawn_level = fields[8]
    spawn_prob = fields[9]
    sprite = fields[10]
    
    # Nettoyer les guillemets
    gsub(/"/, "", id)
    gsub(/"/, "", name)
    gsub(/"/, "", description)
    gsub(/"/, "", type_field)
    gsub(/"/, "", price)
    gsub(/"/, "", mana)
    gsub(/"/, "", max_uses)
    gsub(/"/, "", spawn_level)
    gsub(/"/, "", spawn_prob)
    gsub(/"/, "", sprite)
    
    # Afficher les résultats
    print "---"
    print "ID: " id
    print "Name: " name
    print "Description: " description
    print "Type: " type_field
    print "Price: " price
    print "Mana: " mana
    print "Max Uses: " max_uses
    print "Spawn Level: " spawn_level
    print "Spawn Probability: " spawn_prob
    print "Sprite: " sprite
    
}
' "$CSV_FILE"