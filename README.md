# FMCCitizens plugin
**An addon for Skript, adding support for Citizens oriented features.**

**_Created by Pdani001_**

### Current Skript additions:
- NPC creator:
  - `[create] new npc [with] type %-entitytype% and name %-string%`
    - This returns the numeric ID of the created NPC which you need to save as every method needs it!
    - Case-sensitive!
- NPC delete:
  - `remove npc [with] id %-number%`
- NPC spawn:
  - `spawn npc [with] id %-number% [at|with] location %-location%`
- NPC despawn:
  - `despawn npc [with] id %-number%`
- NPC animate:
  - `animate npc [with] id %-number% [with] animation %-string%`
    - All valid animations can be found at the [Citizens documentation](https://jd.citizensnpcs.co/net/citizensnpcs/util/PlayerAnimation.html#enum.constant.summary)
    - This method is case-insensitive
- NPC teleport:
  - `teleport npc [with] id %-number% [to] location %-location%`
- NPC nameplate:
  - `change npc nameplate [with] id %-number% to %-boolean%`
  - I couldn't get this to work, so if you get this to work, let me know!
- NPC custom name:
  - `change npc customname [with] id %-number% to %-string%`
  - Supports color coding! (E.g. &7,&e)
- NPC is spawned:
  - `npc [with] id %-number% [is] spawned`
  - If the NPC with the given ID doesn't exists, or the given ID is null, this returns null