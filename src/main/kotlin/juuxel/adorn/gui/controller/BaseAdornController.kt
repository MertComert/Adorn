package juuxel.adorn.gui.controller

import io.github.cottonmc.cotton.gui.CottonScreenController
import io.github.cottonmc.cotton.gui.EmptyInventory
import io.github.cottonmc.cotton.gui.widget.WPlayerInvPanel
import juuxel.adorn.gui.widget.Painters
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.container.BlockContext
import net.minecraft.container.PropertyDelegate
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.BasicInventory
import net.minecraft.inventory.Inventory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.apache.logging.log4j.LogManager
import java.util.*

abstract class BaseAdornController(
    syncId: Int,
    playerInv: PlayerInventory,
    context: BlockContext,
    blockInventory: Inventory,
    propertyDelegate: PropertyDelegate = getBlockPropertyDelegate(context)
) : CottonScreenController(
    null, syncId, playerInv, blockInventory, propertyDelegate
) {
    protected val playerInvPanel by lazy { createPlayerInventoryPanel() }

    override fun canUse(player: PlayerEntity?) = true

    override fun addPainters() {
        playerInvPanel.setBackgroundPainter(Painters.LIBGUI_STYLE_SLOT)
    }

    companion object {
        private val LOGGER = LogManager.getLogger()

        fun getBlock(context: BlockContext): Optional<Block> =
            context.run<Block> { world: World, pos: BlockPos ->
                world.getBlockState(pos).block
            }

        fun getBlockEntity(context: BlockContext): BlockEntity? =
            context.run<BlockEntity?> { world: World, pos: BlockPos ->
                world.getBlockEntity(pos)
            }.orElse(null)

        fun getBlockInventoryOrCreate(context: BlockContext, fallbackSize: Int) =
            getBlockInventory(context).let {
                if (it is EmptyInventory)
                    when (fallbackSize) {
                        0 -> EmptyInventory.INSTANCE
                        else -> {
                            LOGGER.warn(
                                "[Adorn] No block inventory found at {}",
                                context.run<BlockPos> { _, pos -> pos }
                                    .map(BlockPos::toString)
                                    .orElse("missing position")
                            )
                            BasicInventory(fallbackSize)
                        }
                    }
                else it
            }
    }
}
