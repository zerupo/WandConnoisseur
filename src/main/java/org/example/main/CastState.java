package org.example.main;

import org.example.menu.MenuTree;
import org.example.projectiles.Projectile;
import org.example.script.Script;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

class CastStateProjectile{
    private Projectile projectile;
    private int count;

    public CastStateProjectile(Projectile projectile){
        this.projectile = projectile;
        this.count = 1;
    }

    public CastStateProjectile(Projectile projectile, int count){
        this.projectile = projectile;
        this.count = count;
    }

    public CastStateProjectile clone(){
        return new CastStateProjectile(this.projectile.clone(), this.count);
    }

    // getters
    public Projectile getProjectile(){
        return this.projectile;
    }

    public int getCount(){
        return this.count;
    }

    // setters
    public void setProjectile(Projectile projectile){
        this.projectile = projectile;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void addCount(int count){
        this.count += count;
    }

    public void addCount(){
        this.count++;
    }
}

class CastStateScript{
    private Script script;
    private int count;

    public CastStateScript(Script script){
        this.script = script;
        this.count = 1;
    }

    public CastStateScript(Script script, int count){
        this.script = script;
        this.count = count;
    }

    public CastStateScript clone(){
        return new CastStateScript(this.script.clone(), this.count);
    }

    // getters
    public Script getScript(){
        return this.script;
    }

    public int getCount(){
        return this.count;
    }

    // setters
    public void setScript(Script script){
        this.script = script;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void addCount(int count){
        this.count += count;
    }

    public void addCount(){
        this.count++;
    }
}

public class CastState{
    private static final BufferedImage triggerImage = Global.loadImage("./src/main/java/org/example/image/other/projectile_trigger.png");
    private static final BufferedImage timerImage = Global.loadImage("./src/main/java/org/example/image/other/projectile_timer.png");
    private static final BufferedImage expirationImage = Global.loadImage("./src/main/java/org/example/image/other/projectile_expiration.png");
    private final ArrayList<CastStateProjectile> castStateProjectiles = new ArrayList<>();
    private final ArrayList<CastStateScript> castStateScripts = new ArrayList<>();
    private int castDelay = 0;
    private DamageComponent damageComponent = new DamageComponent();
    private int lifetime = 0;
    private int critRate = 0;
    private int pattern = 0;
    private double spread = 0;

    public CastState(){
        // nothing
    }

    public CastState clone(){
        CastState castState = new CastState();

        for(CastStateProjectile castStateProjectile : this.castStateProjectiles){
            castState.castStateProjectiles.add(castStateProjectile.clone());
        }
        for(CastStateScript castStateScript : this.castStateScripts){
            castState.castStateScripts.add(castStateScript.clone());
        }
        castState.castDelay = this.castDelay;
        castState.damageComponent = this.damageComponent.clone();
        castState.lifetime = this.lifetime;
        castState.critRate = this.critRate;
        castState.pattern = this.pattern;
        castState.spread = this.spread;

        return castState;
    }

    // getters
    public CastStateProjectile[] getCastStateProjectile(){
        return this.castStateProjectiles.toArray(new CastStateProjectile[0]);
    }

    public CastStateScript[] getCastStateScript(){
        return this.castStateScripts.toArray(new CastStateScript[0]);
    }

    public int getCastDelay(){
        return this.castDelay;
    }

    public DamageComponent getDamageComponent(){
        return this.damageComponent;
    }

    public int getLifetime(){
        return this.lifetime;
    }

    public int getCritRate(){
        return this.critRate;
    }

    public int getPattern(){
        return this.pattern;
    }

    public double getSpread(){
        return this.spread;
    }

    // setters
    public void setCastDelay(int castDelay){
        this.castDelay = castDelay;
    }

    public void setDamage(DamageComponent damageComponent){
        this.damageComponent.setDamage(damageComponent);
    }

    public void setDamageComponent(DamageComponent damageComponent){
        this.damageComponent = damageComponent;
    }

    public void setLifetime(int lifetime){
        this.lifetime = lifetime;
    }

    public void setCritRate(int critRate){
        this.critRate = critRate;
    }

    public void setPattern(int pattern){
        this.pattern = pattern;
    }

    public void setSpread(double spread){
        this.spread = spread;
    }

    // adders
    public void addCastDelay(int castDelay){
        this.castDelay += castDelay;
    }

    public void addDamageComponent(DamageComponent damageComponent){
        this.damageComponent.add(damageComponent);
    }

    public void addLifetime(int lifetime){
        this.lifetime += lifetime;
    }

    public void addCritRate(int critRate){
        this.critRate += critRate;
    }

    public void addPattern(int pattern){
        this.pattern += pattern;
    }

    public void addSpread(double spread){
        this.spread += spread;
    }

    public void addProjectile(Projectile projectile){
        if(projectile.getTriggerType() == Projectile.TriggerType.none){
            for(CastStateProjectile castStateProjectile : this.castStateProjectiles){
                if(castStateProjectile.getProjectile().getClass() == projectile.getClass()){
                    castStateProjectile.addCount();
                    return;
                }
            }
        }
        this.castStateProjectiles.add(new CastStateProjectile(projectile));
    }

    public void addScript(Script script){
        for(CastStateScript castStateScript : this.castStateScripts){
            if(castStateScript.getScript().getClass() == script.getClass()){
                castStateScript.addCount();
                return;
            }
        }
        this.castStateScripts.add(new CastStateScript(script));
    }

    public void addScript(Script[] scripts){
        for(Script script : scripts){
            this.addScript(script);
        }
    }

    public CastState addProjectileTrigger(Projectile projectile, int timer, Projectile.TriggerType triggerType){
        CastState newCastState = new CastState();

        projectile.addTrigger(triggerType, timer, newCastState);
        this.addProjectile(projectile);

        return newCastState;
    }

    public CastState addProjectileTrigger(Projectile projectile, Projectile.TriggerType triggerType){
        return this.addProjectileTrigger(projectile, 0, triggerType);
    }

    public String toString(boolean discordFormat){
        StringBuilder result = new StringBuilder();
        StringBuilder innerBuilder = new StringBuilder();
        ArrayList<Projectile> soloProjectiles = null;
        ArrayList<CastStateProjectile> multiProjectiles = null;
        ArrayList<Script> soloScript = null;
        ArrayList<CastStateScript> multiScript = null;
        boolean skipFlag;

        if(discordFormat){
            soloProjectiles = new ArrayList<>();
            multiProjectiles = new ArrayList<>();
            soloScript = new ArrayList<>();
            multiScript = new ArrayList<>();

            for(CastStateProjectile castStateProjectile : castStateProjectiles){
                if(castStateProjectile.getCount() > 1){
                    multiProjectiles.add(castStateProjectile.clone());
                }else{
                    soloProjectiles.add(castStateProjectile.getProjectile());
                }
            }

            for(int i=0; i < soloProjectiles.size(); i++){
                skipFlag = false;
                for(CastStateProjectile castStateProjectile : multiProjectiles){
                    if(soloProjectiles.get(i).getClass() == castStateProjectile.getProjectile().getClass()){
                        soloProjectiles.remove(i);
                        castStateProjectile.addCount();
                        i--;
                        skipFlag = true;
                        break;
                    }
                }
                if(skipFlag){
                    continue;
                }
                for(int j=i+1; j < soloProjectiles.size(); j++){
                    if(soloProjectiles.get(i).getClass() == soloProjectiles.get(j).getClass()){
                        soloProjectiles.remove(j);
                        multiProjectiles.add(new CastStateProjectile(soloProjectiles.remove(i), 2));
                        i--;
                        break;
                    }
                }
            }

            for(CastStateScript castStateScript : this.castStateScripts){
                if(castStateScript.getCount() > 1){
                    multiScript.add(castStateScript.clone());
                }else{
                    soloScript.add(castStateScript.getScript());
                }
            }

            for(Projectile projectile : soloProjectiles){
                innerBuilder.append(projectile.getEmote());
            }
            for(CastStateProjectile castStateProjectile : multiProjectiles){
                if(!innerBuilder.isEmpty()){
                    innerBuilder.append("\n");
                }
                innerBuilder.append(castStateProjectile.getProjectile().getEmote()).append(" (x").append(castStateProjectile.getCount()).append(")");
            }
            if(!innerBuilder.isEmpty()){
                if(!result.isEmpty()){
                    result.append("\n");
                }
                result.append("# Projectiles\n").append(innerBuilder);
                innerBuilder.setLength(0);
            }
        }

        if(this.castDelay != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append(String.format("Cast delay: %1$df (%2$3.2fs)", this.castDelay, this.castDelay/60.0));}
        if(this.damageComponent.getProjectile() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Projectile damage: ").append(this.damageComponent.getProjectile());}
        if(this.damageComponent.getMelee() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Melee damage: ").append(this.damageComponent.getMelee());}
        if(this.damageComponent.getExplosion() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Explosion damage: ").append(this.damageComponent.getExplosion());}
        if(this.damageComponent.getElectricity() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Electricity damage: ").append(this.damageComponent.getElectricity());}
        if(this.damageComponent.getFire() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Fire damage: ").append(this.damageComponent.getFire());}
        if(this.damageComponent.getDrill() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Drill damage: ").append(this.damageComponent.getDrill());}
        if(this.damageComponent.getSlice() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Slice damage: ").append(this.damageComponent.getSlice());}
        if(this.damageComponent.getIce() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Ice damage: ").append(this.damageComponent.getIce());}
        if(this.damageComponent.getHealing() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Healing damage: ").append(this.damageComponent.getHealing());}
        if(this.damageComponent.getPhysics_hit() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Physics_hit damage: ").append(this.damageComponent.getPhysics_hit());}
        if(this.damageComponent.getRadioactive() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Radioactive damage: ").append(this.damageComponent.getRadioactive());}
        if(this.damageComponent.getPoison() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Poison damage: ").append(this.damageComponent.getPoison());}
        if(this.damageComponent.getOvereating() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Overeating damage: ").append(this.damageComponent.getOvereating());}
        if(this.damageComponent.getCurse() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Curse damage: ").append(this.damageComponent.getCurse());}
        if(this.damageComponent.getHealing() != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Holy damage: ").append(this.damageComponent.getHealing());}
        if(this.lifetime != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append(String.format("Lifetime: %1$df (%2$3.2fs)", this.lifetime, this.lifetime/60.0));}
        if(this.critRate != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Crit rate: ").append(this.critRate).append("%");}
        if(this.pattern != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Pattern: ").append(this.pattern).append(discordFormat ? "°" : " deg");}
        if(this.spread != 0){innerBuilder.append(innerBuilder.isEmpty() ? "" : "\n").append("Spread: ").append(this.spread).append(discordFormat ? "°" : " deg");}

        if(!discordFormat){
            return innerBuilder.toString();
        }

        if(!innerBuilder.isEmpty()){
            if(!result.isEmpty()){
                result.append("\n");
            }
            result.append("# Stats\n").append(innerBuilder);
            innerBuilder.setLength(0);
        }

        for(Script script : soloScript){
            innerBuilder.append(script.getEmote());
        }
        for(CastStateScript castStateScript : multiScript){
            if(!innerBuilder.isEmpty()){
                innerBuilder.append("\n");
            }
            innerBuilder.append(castStateScript.getScript().getEmote()).append(" (x").append(castStateScript.getCount()).append(")");
        }
        if(!innerBuilder.isEmpty()){
            if(!result.isEmpty()){
                result.append("\n");
            }
            result.append("# Scripts\n").append(innerBuilder);
            innerBuilder.setLength(0);
        }

        return result.toString();
    }

    public MenuTree toMenuTree(String id, String title){
        MenuTree menu = new MenuTree("", title, this.toString(true), null, "");
        CastState innerCastState;

        for(CastStateProjectile castStateProjectile : this.castStateProjectiles){
            innerCastState = castStateProjectile.getProjectile().getTriggerCastState();
            if(innerCastState != null){
                menu.addChild(innerCastState.toMenuTree(castStateProjectile.getProjectile().getName() + " " + castStateProjectile.getProjectile().getTriggerType()));
            }
        }
        return menu;
    }

    private MenuTree toMenuTree(String title){
        return this.toMenuTree("", title);
    }

    public static boolean saveToImage(CastState[] castStates, String filename){
        ImageBuilder imageBuilder = new ImageBuilder(new Color(0, 0, 0));
        imageBuilder.setFont(Global.getPixelFont().deriveFont((float)15));
        int y = 0;

        for(int i=0; i < castStates.length; i++){
            y = castStates[i].toImage(imageBuilder, 0, y) + 40;
        }
        return imageBuilder.saveToFile(filename);
    }

    public boolean saveToImage(String filename){
        ImageBuilder image = new ImageBuilder(new Color(0, 0, 0));
        image.setFont(Global.getPixelFont().deriveFont((float)15));
        this.toImageNode(image, 0, 0);
        return image.saveToFile(filename);
    }

    public BufferedImage toImage(){
        ImageBuilder image = new ImageBuilder(new Color(0, 0, 0));
        image.setFont(Global.getPixelFont().deriveFont((float)15));
        this.toImageNode(image, 0, 0);
        return image.toImage();
    }

    public static BufferedImage toImage(CastState[] castStates){
        ImageBuilder imageBuilder = new ImageBuilder(new Color(0, 0, 0));
        imageBuilder.setFont(Global.getPixelFont().deriveFont((float)15));
        int y = 0;

        for(int i=0; i < castStates.length; i++){
            y = castStates[i].toImage(imageBuilder, 0, y) + 40;
        }
        return imageBuilder.toImage();
    }

    private int toImage(ImageBuilder image, int x, int y){
        return (int)this.toImageNode(Objects.requireNonNullElseGet(image, () -> new ImageBuilder(new Color(0, 0, 0))), x, y).getY();
    }

    private Point toImageNode(ImageBuilder image, int x, int y){
        String[] castStateInfo = this.toString(false).split("\\r?\\n");
        CastStateProjectile[] castStateProjectiles = this.castStateProjectiles.toArray(new CastStateProjectile[0]);
        Projectile currentProjectile;
        BufferedImage currentImage;
        int imageSize = 16;
        int arrowSizeX = 40;
        int boxOutsideMargin = 10;
        int boxInsideMargin = 5;
        int nextX = x + boxInsideMargin;
        int nextY = y + boxInsideMargin;
        int triggerY = y;
        Point tempPoint = new Point(x + boxInsideMargin, y + boxInsideMargin);
        ArrayList<Projectile> soloProjectiles = new ArrayList<>();
        ArrayList<CastStateProjectile> multiProjectiles = new ArrayList<>();
        ArrayList<Projectile> triggerProjectiles = new ArrayList<>();
        ArrayList<Script> soloScript = new ArrayList<>();
        ArrayList<CastStateScript> multiScript = new ArrayList<>();
        boolean skipFlag;

        if(castStateInfo.length == 1 && castStateInfo[0].equals("")){
            castStateInfo = new String[0];
        }

        for(CastStateProjectile castStateProjectile : castStateProjectiles){
            currentProjectile = castStateProjectile.getProjectile();
            if(currentProjectile.getTriggerCastState() != null){
                triggerProjectiles.add(currentProjectile);
                soloProjectiles.add(currentProjectile);
                continue;
            }

            if(castStateProjectile.getCount() > 1){
                multiProjectiles.add(castStateProjectile.clone());
            }else{
                soloProjectiles.add(currentProjectile);
            }
        }

        for(int i=0; i < soloProjectiles.size(); i++){
            skipFlag = false;
            for(CastStateProjectile castStateProjectile : multiProjectiles){
                if(soloProjectiles.get(i).getClass() == castStateProjectile.getProjectile().getClass()){
                    soloProjectiles.remove(i);
                    castStateProjectile.addCount();
                    i--;
                    skipFlag = true;
                    break;
                }
            }
            if(skipFlag){
                continue;
            }
            for(int j=i+1; j < soloProjectiles.size(); j++){
                if(soloProjectiles.get(i).getClass() == soloProjectiles.get(j).getClass()){
                    soloProjectiles.remove(j);
                    multiProjectiles.add(new CastStateProjectile(soloProjectiles.remove(i), 2));
                    i--;
                    break;
                }
            }
        }

        for(CastStateScript castStateScript : this.castStateScripts){
            if(castStateScript.getCount() > 1){
                multiScript.add(castStateScript.clone());
            }else{
                soloScript.add(castStateScript.getScript());
            }
        }

        for(Projectile projectile : soloProjectiles){
            currentImage = projectile.getImage();
            if(currentImage != null){
                image.addImage(currentImage, nextX, y + boxInsideMargin);
                nextX += currentImage.getWidth();
                nextY = Math.max(nextY, y + boxInsideMargin + currentImage.getHeight());
            }
        }

        for(CastStateProjectile castStateProjectile : multiProjectiles){
            currentImage = castStateProjectile.getProjectile().getImage();
            if(currentImage != null){
                image.addImage(currentImage, x + boxInsideMargin, nextY);
                tempPoint = image.drawText("x" + castStateProjectile.getCount(), x + boxInsideMargin + currentImage.getWidth(), nextY, Color.WHITE);
                nextX = Math.max(nextX, (int)tempPoint.getX());
                nextY += currentImage.getHeight();
            }
        }

        for(String info : castStateInfo){
            tempPoint = image.drawText(info, x + boxInsideMargin, nextY, Color.WHITE);
            nextY = Math.max(nextY, (int)tempPoint.getY());
            nextX = Math.max(nextX, (int)tempPoint.getX());
        }

        tempPoint.setLocation(x + boxInsideMargin, nextY);
        for(Script script : soloScript){
            currentImage = script.getImage();
            if(currentImage != null){
                image.addImage(currentImage, (int)tempPoint.getX(), (int)tempPoint.getY());
                tempPoint.setLocation(tempPoint.getX() + currentImage.getWidth(), tempPoint.getY());
                nextX = Math.max(nextX, (int)tempPoint.getY());
                nextY = Math.max(nextY, (int)tempPoint.getY() + currentImage.getHeight());
            }
        }

        for(CastStateScript castStateScript : multiScript){
            currentImage = castStateScript.getScript().getImage();
            if(currentImage != null){
                image.addImage(currentImage, x + boxInsideMargin, nextY);
                tempPoint = image.drawText("x" + castStateScript.getCount(), x + boxInsideMargin + currentImage.getWidth(), nextY, Color.WHITE);
                nextX = Math.max(nextX, (int)tempPoint.getX());
                nextY += currentImage.getHeight();
            }
        }

        nextX += boxInsideMargin;
        nextY += boxInsideMargin;
        nextX = Math.max(nextX, x + imageSize);
        nextY = Math.max(nextY, y + imageSize);
        image.drawRectangle(x, y, nextX, nextY, Color.WHITE);

        for(Projectile projectile : triggerProjectiles){
            if(triggerY != y){
                triggerY += boxOutsideMargin;
            }
            image.drawArrow(nextX, y + imageSize/2, nextX + arrowSizeX, triggerY + imageSize/2, Color.WHITE, true, false);
            currentImage = projectile.getImage();
            if(currentImage != null){
                image.addImage(currentImage, nextX + arrowSizeX, triggerY);
                switch(projectile.getTriggerType()){
                    case trigger -> {if(triggerImage != null){image.addImage(triggerImage, nextX + arrowSizeX, triggerY);}}
                    case timer -> {if(timerImage != null){image.addImage(timerImage, nextX + arrowSizeX, triggerY);}}
                    case expiration -> {if(expirationImage != null){image.addImage(expirationImage, nextX + arrowSizeX, triggerY);}}
                }
            }
            triggerY = Math.max(triggerY, (int)projectile.getTriggerCastState().toImageNode(image, nextX + arrowSizeX + imageSize, triggerY).getY());
            nextY = Math.max(nextY, triggerY);
        }

        return new Point(nextX, nextY);
    }
}