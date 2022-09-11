package tech.op65n.krompir.module.addon.impl.stonecutter.util;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class StoneCutterEntity implements Entity {

    @NotNull
    @Override
    public Location getLocation() {
        return null;
    }

    @Nullable
    @Override
    public Location getLocation(@Nullable Location loc) {
        return null;
    }

    @Override
    public void setVelocity(@NotNull Vector velocity) {

    }

    @NotNull
    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @NotNull
    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }

    @Override
    public boolean isOnGround() {
        return false;
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @NotNull
    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public void setRotation(float yaw, float pitch) {

    }

    /**
     * Teleports this entity to the given location.
     * <p>
     * Note: Teleporting to a different world with ignorePassengers to true while the entity has entities riding it
     * will cause this teleportation to return false and not occur.
     * Note: Teleporting to a different world with dismount to false while this entity is riding another entity will
     * cause this teleportation to return false and not occur.
     *
     * @param location         New location to teleport this entity to
     * @param cause            The cause of this teleportation
     * @param ignorePassengers If all passengers should not be required to be removed prior to teleportation
     * @param dismount         If the entity should be dismounted if they are riding another entity
     * @return <code>true</code> if the teleport was successful
     */
    @Override
    public boolean teleport(@NotNull Location location, PlayerTeleportEvent.@NotNull TeleportCause cause, boolean ignorePassengers, boolean dismount) {
        return false;
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return false;
    }

    @Override
    public boolean teleport(@NotNull Location location, @NotNull PlayerTeleportEvent.TeleportCause cause) {
        return false;
    }

    @Override
    public boolean teleport(@NotNull Entity destination) {
        return false;
    }

    @Override
    public boolean teleport(@NotNull Entity destination, @NotNull PlayerTeleportEvent.TeleportCause cause) {
        return false;
    }

    @NotNull
    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        return null;
    }

    @Override
    public int getEntityId() {
        return 0;
    }

    @Override
    public int getFireTicks() {
        return 0;
    }

    @Override
    public int getMaxFireTicks() {
        return 0;
    }

    @Override
    public void setFireTicks(int ticks) {

    }

    /**
     * Sets if the entity has visual fire (it will always appear to be on fire).
     *
     * @param fire whether visual fire is enabled
     */
    @Override
    public void setVisualFire(boolean fire) {

    }

    /**
     * Gets if the entity has visual fire (it will always appear to be on fire).
     *
     * @return whether visual fire is enabled
     */
    @Override
    public boolean isVisualFire() {
        return false;
    }

    /**
     * Returns the entity's current freeze ticks (amount of ticks the entity has
     * been in powdered snow).
     *
     * @return int freeze ticks
     */
    @Override
    public int getFreezeTicks() {
        return 0;
    }

    /**
     * Returns the entity's maximum freeze ticks (amount of ticks before it will
     * be fully frozen)
     *
     * @return int max freeze ticks
     */
    @Override
    public int getMaxFreezeTicks() {
        return 0;
    }

    /**
     * Sets the entity's current freeze ticks (amount of ticks the entity has
     * been in powdered snow).
     *
     * @param ticks Current ticks
     */
    @Override
    public void setFreezeTicks(int ticks) {

    }

    /**
     * Gets if the entity is fully frozen (it has been in powdered snow for max
     * freeze ticks).
     *
     * @return freeze status
     */
    @Override
    public boolean isFrozen() {
        return false;
    }

    /**
     * Gets if the entity currently has its freeze ticks locked
     * to a set amount.
     * <p>
     * This is only set by plugins
     *
     * @return locked or not
     */
    @Override
    public boolean isFreezeTickingLocked() {
        return false;
    }

    /**
     * Sets if the entity currently has its freeze ticks locked,
     * preventing default vanilla freeze tick modification.
     *
     * @param locked prevent vanilla modification or not
     */
    @Override
    public void lockFreezeTicks(boolean locked) {

    }

    @Override
    public void remove() {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @NotNull
    @Override
    public Server getServer() {
        return null;
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public void setPersistent(boolean persistent) {

    }

    @Nullable
    @Override
    public Entity getPassenger() {
        return null;
    }

    @Override
    public boolean setPassenger(@NotNull Entity passenger) {
        return false;
    }

    @NotNull
    @Override
    public List<Entity> getPassengers() {
        return null;
    }

    @Override
    public boolean addPassenger(@NotNull Entity passenger) {
        return false;
    }

    @Override
    public boolean removePassenger(@NotNull Entity passenger) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean eject() {
        return false;
    }

    @Override
    public float getFallDistance() {
        return 0;
    }

    @Override
    public void setFallDistance(float distance) {

    }

    @Override
    public void setLastDamageCause(@Nullable EntityDamageEvent event) {

    }

    @Nullable
    @Override
    public EntityDamageEvent getLastDamageCause() {
        return null;
    }

    @NotNull
    @Override
    public UUID getUniqueId() {
        return null;
    }

    @Override
    public int getTicksLived() {
        return 0;
    }

    @Override
    public void setTicksLived(int value) {

    }

    @Override
    public void playEffect(@NotNull EntityEffect type) {

    }

    @NotNull
    @Override
    public EntityType getType() {
        return null;
    }

    @Override
    public boolean isInsideVehicle() {
        return false;
    }

    @Override
    public boolean leaveVehicle() {
        return false;
    }

    @Nullable
    @Override
    public Entity getVehicle() {
        return null;
    }

    @Override
    public void setCustomNameVisible(boolean flag) {

    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public void setGlowing(boolean flag) {

    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public void setInvulnerable(boolean flag) {

    }

    @Override
    public boolean isInvulnerable() {
        return false;
    }

    @Override
    public boolean isSilent() {
        return false;
    }

    @Override
    public void setSilent(boolean flag) {

    }

    @Override
    public boolean hasGravity() {
        return false;
    }

    @Override
    public void setGravity(boolean gravity) {

    }

    @Override
    public int getPortalCooldown() {
        return 0;
    }

    @Override
    public void setPortalCooldown(int cooldown) {

    }

    @NotNull
    @Override
    public Set<String> getScoreboardTags() {
        return null;
    }

    @Override
    public boolean addScoreboardTag(@NotNull String tag) {
        return false;
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String tag) {
        return false;
    }

    @NotNull
    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @NotNull
    @Override
    public BlockFace getFacing() {
        return null;
    }

    @NotNull
    @Override
    public Pose getPose() {
        return null;
    }

    /**
     * Get the category of spawn to which this entity belongs.
     *
     * @return the entity´s category spawn
     */
    @Override
    public @NotNull SpawnCategory getSpawnCategory() {
        return null;
    }

    @NotNull
    @Override
    public Spigot spigot() {
        return null;
    }

    /**
     * Gets the name of this command sender
     *
     * @return Name of the sender
     */
    @Override
    public @NotNull Component name() {
        return null;
    }

    /**
     * Gets the entity's display name formatted with their team prefix/suffix and
     * the entity's default hover/click events.
     *
     * @return the team display name
     */
    @Override
    public @NotNull Component teamDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public Location getOrigin() {
        return null;
    }

    @Override
    public boolean fromMobSpawner() {
        return false;
    }

    @NotNull
    @Override
    public Chunk getChunk() {
        return null;
    }

    @NotNull
    @Override
    public CreatureSpawnEvent.SpawnReason getEntitySpawnReason() {
        return null;
    }

    @Override
    public boolean isInRain() {
        return false;
    }

    @Override
    public boolean isInBubbleColumn() {
        return false;
    }

    @Override
    public boolean isInWaterOrRain() {
        return false;
    }

    @Override
    public boolean isInWaterOrBubbleColumn() {
        return false;
    }

    @Override
    public boolean isInWaterOrRainOrBubbleColumn() {
        return false;
    }

    @Override
    public boolean isInLava() {
        return false;
    }

    @Override
    public boolean isTicking() {
        return false;
    }

    /**
     * Returns a set of {@link Player Players} within this entity's tracking range (players that can "see" this entity).
     *
     * @return players in tracking range
     */
    @Override
    public @NotNull Set<Player> getTrackedPlayers() {
        return null;
    }

    /**
     * Spawns the entity in the world at the given {@link Location} with the reason given.
     * <p>
     * This will not spawn the entity if the entity is already spawned or has previously been despawned.
     * <p>
     * Also, this method will fire the same events as a normal entity spawn.
     *
     * @param location The location to spawn the entity at.
     * @param reason   The reason for the entity being spawned.
     * @return Whether the entity was successfully spawned.
     */
    @Override
    public boolean spawnAt(@NotNull Location location, CreatureSpawnEvent.@NotNull SpawnReason reason) {
        return false;
    }

    /**
     * Check if entity is inside powdered snow.
     *
     * @return true if in powdered snow.
     */
    @Override
    public boolean isInPowderedSnow() {
        return false;
    }

    /**
     * Checks for any collisions with the entity's bounding box at the provided location.
     * This will check for any colliding entities (boats, shulkers) / worldborder / blocks.
     * Does not load chunks that are within the bounding box at the specified location.
     *
     * @param location the location to check collisions in
     * @return collides or not
     */
    @Override
    public boolean collidesAt(@NotNull Location location) {
        return false;
    }

    /**
     * This checks using the given boundingbox as the entity's boundingbox if the entity would collide with anything.
     * This will check for any colliding entities (boats, shulkers) / worldborder / blocks.
     * Does not load chunks that are within the bounding box.
     *
     * @param boundingBox the box to check collisions in
     * @return collides or not
     */
    @Override
    public boolean wouldCollideUsing(@NotNull BoundingBox boundingBox) {
        return false;
    }

    /**
     * Gets the custom name.
     *
     * <p>This value has no effect on players, they will always use their real name.</p>
     *
     * @return the custom name
     */
    @Override
    public @Nullable Component customName() {
        return null;
    }

    /**
     * Sets the custom name.
     *
     * <p>This name will be used in death messages and can be sent to the client as a nameplate over the mob.</p>
     *
     * <p>Setting the name to {@code null} will clear it.</p>
     *
     * <p>This value has no effect on players, they will always use their real name.</p>
     *
     * @param customName the custom name to set
     */
    @Override
    public void customName(@Nullable Component customName) {

    }

    @Nullable
    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(@Nullable String name) {

    }

    @Override
    public void sendMessage(@NotNull String message) {

    }

    @Override
    public void sendMessage(@NotNull String[] messages) {

    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {

    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String[] messages) {

    }

    @NotNull
    @Override
    public String getName() {
        return "Stone Cutter";
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {

    }

    @NotNull
    @Override
    public List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        return null;
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {

    }

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        return false;
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull String name) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        return false;
    }

    @NotNull
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        return null;
    }

    @NotNull
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return null;
    }

    @Nullable
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return null;
    }

    @Nullable
    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return null;
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {

    }

    @Override
    public void recalculatePermissions() {

    }

    @NotNull
    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    @Override
    public boolean isOp() {
        return false;
    }

    @Override
    public void setOp(boolean value) {

    }

    @NotNull
    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return null;
    }
}
