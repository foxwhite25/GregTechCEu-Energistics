# GregTech Energistics

# This is a port for GTCEu, 

* The stocker cover and Fluid encoder works functionally.
* But there is some bugs like the pattern will disappear visually when it is in the cover slot.
* Stocker terminal does not work at all

To be honest with you, I have no idea what's wrong, if you can fix it, PR is welcomed.

## Description
Adds GregTech Community Edition and Applied Energistics 2 items/block to add additional interoperability.



Current features:
* GTCE stocker cover that handles passive crafting. Items and fluids are pulled from the attached AE2 network. This cover disables the machine under certain conditions (such as missing input items), greatly reducing GTCE machine lag. Fully compatible with all GTCE machines, including multiblocks (such as EBF). Can be used in conjunction with a crafting upgrade for chained production.

* Stocker terminal for monitoring all stocker covers and their current status.

* Fluid encoder item for configuring fluid crafting inputs/outputs. Used in place of fluids when creating processing patterns.

See https://www.curseforge.com/minecraft/mc-mods/gregtech-energistics for details.
