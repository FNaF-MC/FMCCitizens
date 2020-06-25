package hu.Pdani.FMCCitizens.manager;

import ch.njol.skript.Skript;
import ch.njol.skript.entity.EntityType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

public class NpcIsSpawned extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(NpcIsSpawned.class,Boolean.class, ExpressionType.COMBINED,"npc [with] id %-number% [is] spawned");
    }

    private Expression<Long> id;

    @Override
    protected Boolean[] get(Event e) {
        if(id == null || id.getSingle(e) == null)
            return null;
        NPC target = CitizensAPI.getNPCRegistry().getById(id.getSingle(e).intValue());
        if(target == null)
            return null;
        return new Boolean[]{target.isSpawned()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "npc [with] id %-number% [is] spawned";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Long>) e[0];
        return true;
    }
}
