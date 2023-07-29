/*

 * cmd= calls cmd
 * conc= calls forward config string
 * config= config to main class
 * econfig= config to non main
 * ilist= import listener to main
 * listener= implements listener
 * permission= adds perms to main
 * sender= calls player sender
 * tnull= target null
 * tplayer= target online
 */

package me.hephaestus2023.test.Listeners.Archer;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Vector;


public class ScatterArrow implements Listener {
    private final int numArrows = 10;
    @EventHandler
    public void arrowhit (ProjectileHitEvent event){
        if (!(event.getEntity() instanceof Arrow)) {
            return; // Only process if the projectile is an arrow
        }

        Arrow arrow = (Arrow) event.getEntity();
        Player shooter = (Player) arrow.getShooter();

        if (shooter == null) {
            return; // If the arrow's shooter is null, don't proceed
        }

        // Now handle the scatter arrow effect
        scatter(arrow.getLocation());
    }

    private void scatter(Location location) {
        double scatterAngle = Math.PI * 2 / numArrows; // Angle between each scatter arrow in radians

        for (int i = 0; i < numArrows; i++) {
            double angle = i * scatterAngle;
            double xVelocity = Math.cos(angle);
            double zVelocity = Math.sin(angle);

            Arrow scatterArrow = (Arrow) location.getWorld().spawnEntity(location, EntityType.ARROW);
            scatterArrow.setShooter(null); // Ensure the scatter arrows won't hit the shooter
            scatterArrow.setVelocity(new Vector(xVelocity, 0.0, zVelocity));
        }

        // Play particle effect and sound for the scatter arrows
        location.getWorld().spawnParticle(Particle.CRIT_MAGIC, location, 30);
        location.getWorld().playSound(location, Sound.ENTITY_ARROW_SHOOT, 1.0f, 1.0f);
    }
}

