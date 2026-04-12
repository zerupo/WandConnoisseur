package org.example.main;

import org.example.projectiles.*;

import java.util.*;
import java.util.function.Predicate;
import org.reflections.Reflections;

public class ProjectileList{
    private Projectile[] projectiles;

    public ProjectileList(Predicate<Projectile> filter, Comparator<Projectile> comparator){
        Reflections reflections = new Reflections("org.example.projectiles");
        Set<Class<? extends Projectile>> subclasses = reflections.getSubTypesOf(Projectile.class);
        List<Projectile> ProjectileList = new ArrayList<>();

        for(Class<? extends Projectile> clazz : subclasses){
            try{
                Projectile projectile = clazz.getDeclaredConstructor().newInstance();
                if(filter.test(projectile)){
                    ProjectileList.add(projectile);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        this.projectiles = ProjectileList.toArray(new Projectile[0]);
        Arrays.sort(this.projectiles, comparator);
    }

    public ProjectileList(Predicate<Projectile> filter){
        this(filter, Comparator.comparing(Projectile::getName));
    }

    public ProjectileList(Comparator<Projectile> comparator){
        this(projectile -> true, comparator);
    }

    public ProjectileList(){
        this(projectile -> true, Comparator.comparing(Projectile::getName));
    }

    public Projectile[] getProjectiles(boolean cloning){
        if(!cloning){
            return this.projectiles;
        }

        System.out.println("CLONING ALL PROJECTILES");
        Projectile[] result = new Projectile[this.projectiles.length];
        for(int i=0; i < result.length; i++){
            result[i] = this.projectiles[i].clone();
        }
        return result;
    }
}