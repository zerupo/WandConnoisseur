#!/bin/bash

# Script d'extraction avancée des sorts Noita depuis gun_actions.lua
# Utilisation: ./extract_spells.sh /chemin/vers/gun_actions.lua

INPUT_FILE="${1:-./gun_actions.lua}"
OUTPUT_FILE="spells_export.csv"

if [ ! -f "$INPUT_FILE" ]; then
    echo "❌ Fichier non trouvé: $INPUT_FILE"
    exit 1
fi

echo "📖 Traitement du fichier: $INPUT_FILE"

# Fonction pour échapper les valeurs CSV
escape_csv() {
    local value="$1"
    if [[ $value == *[,\"\$\n]* ]]; then
        echo "\"${value//\"/\"\"}\""
    else
        echo "$value"
    fi
}

# Initialiser le CSV
{
    echo "ID,Name,Description,Type,Price,Mana,Max Uses,Spawn Level,Spawn Probability,Sprite,Sprite Unidentified,Related Projectiles,Fire Rate Wait,Screenshake,Spread Degrees,Critical Chance,Speed Multiplier,Explosion Radius,Explosion Damage,Lifetime,Material,Recoil Knockback,Knockback Force,Projectile Count,Custom XML,Never Unlimited,Spawn Requires Flag,Action Mods"

    # Extraire chaque sort (entre { et },)
    # Créer des fichiers temporaires pour chaque sort
    awk '
    BEGIN {
        in_spell = 0
        in_multiline_comment = 0
        spell_content = ""
        spell_count = 0
    }
    
    # Gérer les commentaires multilignes --[[ ]]--
    /--\[\[/ {
        in_multiline_comment = 1
    }
    
    /\]\]--/ {
        in_multiline_comment = 0
        next
    }
    
    # Ignorer les lignes dans les commentaires multilignes
    in_multiline_comment {
        next
    }
    
    # Ignorer les lignes de commentaires simples au début
    /^[[:space:]]*--[^[]/ {
        next
    }
    
    /^[[:space:]]*\{[[:space:]]*$/ {
        if (in_spell == 0 && !in_multiline_comment) {
            in_spell = 1
            spell_content = ""
        }
    }
    
    {
        if (in_spell) {
            spell_content = spell_content "\n" $0
        }
    }
    
    /^[[:space:]]*\},[[:space:]]*$/ || /^[[:space:]]*\}\},[[:space:]]*$/ {
        if (in_spell && !in_multiline_comment) {
            in_spell = 0
            spell_count++
            # Écrire le contenu du sort dans un fichier temporaire
            print spell_content > ("/tmp/spell_" spell_count ".tmp")
            spell_content = ""
        }
    }
    ' "$INPUT_FILE"

    # Traiter chaque sort trouvé
    for spell_file in /tmp/spell_*.tmp; do
        if [ -f "$spell_file" ]; then
            # Extraire les champs simples
            id=$(grep -oP "id\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            name=$(grep -oP "name\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            description=$(grep -oP "description\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            sprite=$(grep -oP "sprite\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            sprite_unid=$(grep -oP "sprite_unidentified\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            type=$(grep -oP "type\s*=\s*\K[^,\n]*" "$spell_file" | head -1)
            price=$(grep -oP "price\s*=\s*\K[0-9]*" "$spell_file" | head -1)
            mana=$(grep -oP "mana\s*=\s*\K[0-9.]*" "$spell_file" | head -1)
            max_uses=$(grep -oP "max_uses\s*=\s*\K[0-9-]*" "$spell_file" | head -1)
            spawn_level=$(grep -oP "spawn_level\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            spawn_probability=$(grep -oP "spawn_probability\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            custom_xml=$(grep -oP "custom_xml_file\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)
            never_unlimited=$(grep -oP "never_unlimited\s*=\s*\K[^,\n]*" "$spell_file" | head -1)
            spawn_requires_flag=$(grep -oP "spawn_requires_flag\s*=\s*\"\K[^\"]*" "$spell_file" | head -1)

            # Extraire les projectiles
            projectiles=$(grep -oP "related_projectiles\s*=\s*\{\K[^}]*" "$spell_file" | sed 's/"[^"]*"\|,//g' | tr '\n' ';' | sed 's/;$//')
            projectile_count=$(echo "$projectiles" | grep -o "data/entities" | wc -l)

            # Extraire les modifications des propriétés dans la fonction action
            action_mods=$(grep -oE "c\.[a-z_]+\s*=\s*[^,;\n]*|add_projectile[^(]*\([^)]*\)|add_projectile_trigger[^(]*\([^)]*\)" "$spell_file" | tr '\n' '; ')
            
            # Extraire les valeurs spécifiques
            fire_rate_wait=$(echo "$action_mods" | grep -oP "c\.fire_rate_wait\s*=\s*\K[^;]*" | head -1)
            screenshake=$(echo "$action_mods" | grep -oP "c\.screenshake\s*=\s*\K[^;]*" | head -1)
            spread_degrees=$(echo "$action_mods" | grep -oP "c\.spread_degrees\s*=\s*\K[^;]*" | head -1)
            critical_chance=$(echo "$action_mods" | grep -oP "c\.damage_critical_chance\s*=\s*\K[^;]*" | head -1)
            speed_mult=$(echo "$action_mods" | grep -oP "c\.speed_multiplier\s*=\s*\K[^;]*" | head -1)
            explosion_radius=$(echo "$action_mods" | grep -oP "c\.explosion_radius\s*=\s*\K[^;]*" | head -1)
            explosion_damage=$(echo "$action_mods" | grep -oP "c\.damage_explosion_add\s*=\s*\K[^;]*" | head -1)
            lifetime=$(echo "$action_mods" | grep -oP "c\.lifetime_add\s*=\s*\K[^;]*" | head -1)
            material=$(echo "$action_mods" | grep -oP "c\.material\s*=\s*\K[^;]*" | head -1)
            recoil=$(echo "$action_mods" | grep -oP "shot_effects\.recoil_knockback\s*=\s*\K[^;]*" | head -1)
            knockback=$(echo "$action_mods" | grep -oP "c\.knockback_force\s*=\s*\K[^;]*" | head -1)

            # Construire la ligne CSV
            printf '%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n' \
                "$(escape_csv "$id")" \
                "$(escape_csv "$name")" \
                "$(escape_csv "$description")" \
                "$(escape_csv "$type")" \
                "$(escape_csv "$price")" \
                "$(escape_csv "$mana")" \
                "$(escape_csv "$max_uses")" \
                "$(escape_csv "$spawn_level")" \
                "$(escape_csv "$spawn_probability")" \
                "$(escape_csv "$sprite")" \
                "$(escape_csv "$sprite_unid")" \
                "$(escape_csv "$projectiles")" \
                "$(escape_csv "$fire_rate_wait")" \
                "$(escape_csv "$screenshake")" \
                "$(escape_csv "$spread_degrees")" \
                "$(escape_csv "$critical_chance")" \
                "$(escape_csv "$speed_mult")" \
                "$(escape_csv "$explosion_radius")" \
                "$(escape_csv "$explosion_damage")" \
                "$(escape_csv "$lifetime")" \
                "$(escape_csv "$material")" \
                "$(escape_csv "$recoil")" \
                "$(escape_csv "$knockback")" \
                "$(escape_csv "$projectile_count")" \
                "$(escape_csv "$custom_xml")" \
                "$(escape_csv "$never_unlimited")" \
                "$(escape_csv "$spawn_requires_flag")" \
                "$(escape_csv "$action_mods")"

            rm "$spell_file"
        fi
    done

} > "$OUTPUT_FILE"

# Afficher les statistiques
total_spells=$(tail -n +2 "$OUTPUT_FILE" | wc -l)
echo ""
echo "✅ Extraction terminée!"
echo "📊 Statistiques:"
echo "   • Fichier de sortie: $OUTPUT_FILE"
echo "   • Sorts extraits: $total_spells"
echo "   • Colonnes: 28"
echo ""
echo "📋 Colonnes disponibles:"
echo "   - ID, Name, Description, Type, Price, Mana, Max Uses"
echo "   - Spawn Level, Spawn Probability"
echo "   - Sprite, Sprite Unidentified, Related Projectiles, Projectile Count"
echo "   - Fire Rate Wait, Screenshake, Spread Degrees, Critical Chance"
echo "   - Speed Multiplier, Explosion Radius, Explosion Damage, Lifetime"
echo "   - Material, Recoil Knockback, Knockback Force"
echo "   - Custom XML, Never Unlimited, Spawn Requires Flag"
echo "   - Action Mods (toutes les modifications)"