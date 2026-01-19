#!/bin/bash

# Parseur CSV robuste pour extraire les traductions depuis common.csv
# Utilisation: ./parse_common_csv.sh common.csv

CSV_FILE="${1:-common.csv}"

if [ ! -f "$CSV_FILE" ]; then
    echo "Fichier non trouvé: $CSV_FILE"
    exit 1
fi

# Parser le CSV en respectant les guillemets
awk '
BEGIN {
    FS = ","
}

NR == 1 {
    # Parser le header pour trouver l''index de la colonne English
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
    
    # Afficher les colonnes
    print "Colonnes trouvees:" > "/dev/stderr"
    for (i = 1; i <= field_num; i++) {
        col = fields[i]
        gsub(/"/, "", col)
        printf "%2d: %s\n", i, col > "/dev/stderr"
        if (col == "en") {
            english_col = i
        }
    }
    print "English column index: " english_col > "/dev/stderr"
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
    
    # Extraire les donnees
    key = fields[1]
    english = fields[2]
    
    # Nettoyer les guillemets
    gsub(/"/, "", key)
    gsub(/"/, "", english)
    
    # Afficher pour verification
    if (key != "") {
        print "---"
        print "Key: " key
        print "English: " english
    }
}
' "$CSV_FILE"