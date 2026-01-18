# Scripts d'Extraction Noita

Suite de scripts bash pour extraire les données des sorts Noita, générer des classes Java et gérer les ressources associées.

## Vue d'ensemble

```
gun_actions.lua
    ↓ spell_to_csv.sh
spells_export.csv
    ├→ generate_java_spells.sh → spells_java/*.java
    ├→ add_lua_comment.sh → spells_java/*.java (avec commentaires Lua)
    └→ copy_sprites.sh → filtered/*.png
```

## Scripts

### 1. spell_to_csv.sh

Extraction des sorts du fichier Lua vers CSV.

```bash
./spell_to_csv.sh gun_actions.lua
```

Génère `spells_export.csv` avec 28 colonnes: ID, Name, Description, Type, Price, Mana, Max Uses, Spawn Level, Spawn Probability, Sprite, Sprite Unidentified, Related Projectiles, Fire Rate Wait, Screenshake, Spread Degrees, Critical Chance, Speed Multiplier, Explosion Radius, Explosion Damage, Lifetime, Material, Recoil Knockback, Knockback Force, Projectile Count, Custom XML, Never Unlimited, Spawn Requires Flag, Action Mods.

### 2. parse_csv.sh

Vérification du parsing CSV.

```bash
./parse_csv.sh spells_export.csv
```

Affiche les données extraites pour chaque sort au format lisible.

### 3. generate_java_spells.sh

Génération des classes Java complètes.

```bash
./generate_java_spells.sh spells_export.csv common.csv ./spells_java
```

Arguments:
- `spells_export.csv`: CSV généré par spell_to_csv.sh
- `common.csv`: Fichier de traductions (colonnes: clé, English, ...)
- `./spells_java`: Dossier de sortie

Caractéristiques:
- Parse correctement le CSV avec guillemets imbriqués
- Mappe Spawn Level + Spawn Probability aux indices 0-10
- Récupère noms/descriptions depuis common.csv avec fallback
- Détecte les charges (hasCharges, maxCharges)
- Inclut placeholders commentés pour alias et emote

### 4. add_lua_comment.sh

Ajout du code Lua original en commentaire dans les fichiers Java.

```bash
./add_lua_comment.sh ./gun_actions.lua ./spells_java
```

Arguments:
- `./gun_actions.lua`: Fichier source
- `./spells_java`: Dossier des fichiers Java

Extrait précisément le sort correspondant et l'ajoute en commentaire avant la fermeture de classe.

### 5. copy_sprites.sh

Copie des fichiers PNG des sprites.

```bash
./copy_sprites.sh spells_export.csv ./gun_actions
```

Arguments:
- `spells_export.csv`: Fichier CSV
- `./gun_actions`: Dossier contenant les images (trouvable dans data) https://noita.wiki.gg/wiki/Modding:_Data.wak

Crée `filtered/` avec les images copiées, ignore les sprites _unidentified.

## Workflow complet

```bash
# 1. Extraction
./spell_to_csv.sh gun_actions.lua

# 2. Vérification (optionnel)
./parse_csv.sh spells_export.csv | head -50

# 3. Génération Java
./generate_java_spells.sh spells_export.csv common.csv ./spells_java

# 4. Ajout commentaires Lua
./add_lua_comment.sh ./gun_actions.lua ./spells_java
 
 =======================

# Copie sprites (annexe)
./copy_sprites.sh spells_export.csv ./gun_actions
```

## Dépendances

bash (4.0+), awk, sed, grep, find, cut

## Format attendu

### gun_actions.lua
```lua
actions = {
    {
        id          = "BOMB_CART",
        name        = "$action_bomb_cart",
        description = "$actiondesc_bomb_cart",
        sprite      = "data/ui_gfx/gun_actions/bomb_cart.png",
        type        = ACTION_TYPE_PROJECTILE,
        spawn_level = "2,3,4,5,6",
        spawn_probability = "0.6,0.6,0.5,0.8,0.6",
        price = 200,
        mana = 75,
        max_uses = 6,
    },
}
```

### common.csv
```csv
,en,ru,pt-br,...
action_bomb_cart,Bomb Cart,...
actiondesc_bomb_cart,Summons a self-propelled mine cart,...
```

## Troubleshooting

**Valeurs mal extraites du CSV**: Utiliser parse_csv.sh pour déboguer.

**Traductions manquantes**: Les noms fallback seront générés automatiquement depuis l'ID.

**Images ne se copient pas**: Vérifier que le dossier existe et contient un sous-dossier gun_actions.

**Commentaires Lua manquants**: Vérifier le format du fichier gun_actions.lua (les sorts commentés en Lua sont ignorés).

## Résultat attendu

- spells_export.csv avec ~422 sorts
- spells_java/ avec ~422 fichiers .java
- filtered/ avec les images PNG
- Chaque .java contient le code Lua en commentaire