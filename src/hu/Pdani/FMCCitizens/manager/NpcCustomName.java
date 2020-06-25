package hu.Pdani.FMCCitizens.manager;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import hu.Pdani.FMCCitizens.registry.DeleteNpc;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;

public class NpcCustomName extends Effect {

    static {
        Skript.registerEffect(NpcCustomName.class,"change npc customname [with] id %-number% to %-string%");
    }

    private Expression<Long> id;
    private Expression<String> sval;

    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        if(sval == null || sval.getSingle(e) == null)
            return;
        NPC target = CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue());
        if(target == null || !target.isSpawned())
            return;
        target.getEntity().setCustomName(ChatColor.translateAlternateColorCodes('&',sval.getSingle(e)));
    }

    @Override
    public String toString(Event event, boolean b) {
        return "change npc customname [with] id %-number% to %-string%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        sval = (Expression<String>) e[1];
        return true;
    }
}
