package hu.Pdani.FMCCitizens.manager;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.Event;

public class NpcCustomSkin extends Effect {

    static {
        Skript.registerEffect(NpcCustomSkin.class,"change npc skin [with] id %-number% to %-string%");
    }

    private Expression<Long> id;
    private Expression<String> sval;

    @SuppressWarnings("deprecation")
    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        if(sval == null || sval.getSingle(e) == null)
            return;
        NPC target = CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue());
        if(target == null || !target.isSpawned())
            return;
        target.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, sval.getSingle(e));
        target.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, true);

        // send skin change to online players by removing and adding this fake player
        if (target.isSpawned()) {
            Location loc = target.getStoredLocation();
            target.despawn();
            target.spawn(loc);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "change npc skin [with] id %-number% to %-string%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        sval = (Expression<String>) e[1];
        return true;
    }
}
