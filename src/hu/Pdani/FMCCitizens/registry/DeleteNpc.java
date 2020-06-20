package hu.Pdani.FMCCitizens.registry;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.PlayerAnimation;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class DeleteNpc extends Effect {

    static {
        Skript.registerEffect(DeleteNpc.class,"remove npc [with] id %number-%");
    }

    private Expression<Long> id;

    @Override
    protected void execute(Event e){
        if(id == null || id.getSingle(e) == null)
            return;
        NPC target = CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue());
        CitizensAPI.getNPCRegistry().deregister(target);
    }

    @Override
    public String toString(Event event, boolean b) {
        return "remove npc [with] id %number-%";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        return true;
    }
}
