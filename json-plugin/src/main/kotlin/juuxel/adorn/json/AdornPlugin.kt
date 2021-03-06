package juuxel.adorn.json

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.frontend.i18n.ResourceBundleI18n
import io.github.cottonmc.jsonfactory.frontend.plugin.Plugin
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.gens.block.SuffixedBlockItemModel
import io.github.cottonmc.jsonfactory.gens.block.SuffixedBlockState
import io.github.cottonmc.jsonfactory.gens.block.SuffixedLootTable
import juuxel.adorn.json.chair.*
import juuxel.adorn.json.crate.CrateBlockModel
import juuxel.adorn.json.drawer.*
import juuxel.adorn.json.kitchen.*
import juuxel.adorn.json.lamp.*
import juuxel.adorn.json.platform.*
import juuxel.adorn.json.post.*
import juuxel.adorn.json.shelf.ShelfBlockModel
import juuxel.adorn.json.shelf.ShelfBlockState
import juuxel.adorn.json.shelf.ShelfRecipe
import juuxel.adorn.json.sofa.*
import juuxel.adorn.json.step.*
import juuxel.adorn.json.table.*

object AdornPlugin : Plugin {
    //adorn:red,adorn:black,adorn:green,adorn:brown,adorn:blue,adorn:purple,adorn:cyan,adorn:light_gray,adorn:gray,adorn:pink,adorn:lime,adorn:yellow,adorn:light_blue,adorn:magenta,adorn:orange,adorn:white
    //adorn:red_table_lamp,adorn:black_table_lamp,adorn:green_table_lamp,adorn:brown_table_lamp,adorn:blue_table_lamp,adorn:purple_table_lamp,adorn:cyan_table_lamp,adorn:light_gray_table_lamp,adorn:gray_table_lamp,adorn:pink_table_lamp,adorn:lime_table_lamp,adorn:yellow_table_lamp,adorn:light_blue_table_lamp,adorn:magenta_table_lamp,adorn:orange_table_lamp,adorn:white_table_lamp
    //adorn:oak,adorn:spruce,adorn:birch,adorn:jungle,adorn:acacia,adorn:dark_oak
    //adorn:stone,adorn:cobblestone,adorn:sandstone,adorn:andesite,adorn:granite,adorn:diorite
    //adorn:terrestria_cypress,adorn:terrestria_hemlock,adorn:terrestria_japanese_maple,adorn:terrestria_rainbow_eucalyptus,adorn:terrestria_redwood,adorn:terrestria_rubber,adorn:terrestria_sakura,adorn:terrestria_willow,adorn:traverse_fir

    private val planksItem = fun(id: Identifier) = Identifier.mc(id.path + "_planks")
    private val slabItem = fun(id: Identifier) = Identifier.mc(id.path + "_slab")
    private val woolItem = fun(id: Identifier) = Identifier.mc(id.path + "_wool")
    private val carpetItem = fun(id: Identifier) = Identifier.mc(id.path + "_carpet")
    private val selfItem = fun(id: Identifier) = Identifier.mc(id.path)

    private fun constItem(const: Identifier) = fun(_: Identifier) = const

    override val i18n = ResourceBundleI18n("assets.adorn.lang")

    val SOFA = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Sofas
    )
    val CHAIR = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Chairs
    )
    val TABLE = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Tables
    )
    val KITCHEN = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Kitchen
    )
    val DRAWER = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Drawers
    )

    val POST = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Posts
    )
    val PLATFORM = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Platforms
    )
    val STEP = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Steps
    )

    val SHELF = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Shelves
    )

    val OTHER = GeneratorInfo(
        AdornCategory,
        AdornPlugin.Subcategories.Other
    )

    override val generators: List<ContentGenerator> = listOf(
        SofaBlockModel,
        SofaBlockState,
        SofaItemModel,
        SuffixedLootTable("sofa", SOFA),
        SofaRecipe,
        SuffixedRecipeAdvancementGenerator(
            "sofa.recipe_advancement",
            SOFA,
            "sofa",
            keyItems = listOf(woolItem)
        ),

        ChairBlockModel,
        ChairBlockState,
        ChairItemModel,
        ChairLootTable,
        ChairRecipe,
        SuffixedRecipeAdvancementGenerator(
            "chair.recipe_advancement",
            CHAIR,
            "chair",
            keyItems = listOf(slabItem)
        ),

        TableBlockModel,
        TableBlockState,
        TableItemModel,
        SuffixedLootTable("table", TABLE),
        TableRecipe,
        SuffixedRecipeAdvancementGenerator(
            "table.recipe_advancement",
            TABLE,
            "table",
            keyItems = listOf(slabItem)
        ),

        KitchenCounterBlockModel,
        KitchenCounterBlockState,
        SuffixedBlockItemModel("kitchen_counter", KITCHEN),
        SuffixedLootTable("kitchen_counter", KITCHEN),
        KitchenCounterRecipe,
        SuffixedRecipeAdvancementGenerator(
            "kitchen_counter.recipe_advancement",
            KITCHEN,
            "kitchen_counter",
            keyItems = listOf(planksItem)
        ),

        KitchenSinkBlockModel,
        KitchenSinkBlockState,
        SuffixedBlockItemModel("kitchen_sink", KITCHEN),
        SuffixedLootTable("kitchen_sink", KITCHEN),
        KitchenSinkRecipe,
        SuffixedRecipeAdvancementGenerator(
            "kitchen_sink.recipe_advancement",
            KITCHEN,
            "kitchen_sink",
            keyItems = listOf(planksItem)
        ),

        KitchenCupboardBlockModel,
        KitchenCupboardBlockState,
        KitchenCupboardItemModel,
        SuffixedContainerLootTable("kitchen_cupboard", KITCHEN),
        KitchenCupboardRecipe,
        SuffixedRecipeAdvancementGenerator(
            "kitchen_cupboard.recipe_advancement",
            KITCHEN,
            "kitchen_cupboard",
            keyItems = listOf(planksItem, { it.suffixPath("_kitchen_counter") })
        ),

        DrawerBlockModel,
        DrawerBlockState,
        SuffixedBlockItemModel("drawer", DRAWER),
        SuffixedContainerLootTable("drawer", DRAWER),
        DrawerRecipe,
        SuffixedRecipeAdvancementGenerator(
            "drawer.recipe_advancement",
            DRAWER,
            "drawer",
            keyItems = listOf(slabItem)
        ),

        RecipeAdvancementGenerator(
            "trading_station.recipe_advancement",
            OTHER,
            keyItems = listOf(constItem(Identifier.mc("emerald")))
        ),

        WoodenPostBlockModel,
        StonePostBlockModel,
        WoodenPostRecipe,
        StonePostRecipe,
        PostBlockState,
        SuffixedBlockItemModel("post", POST),
        SuffixedLootTable("post", POST),
        SuffixedRecipeAdvancementGenerator(
            "post.wooden.recipe_advancement",
            POST,
            "post",
            keyItems = listOf(planksItem)
        ),
        SuffixedRecipeAdvancementGenerator(
            "post.stone.recipe_advancement",
            POST,
            "post",
            keyItems = listOf(selfItem)
        ),
        SuffixedStonecuttingRecipe(
            "post.stonecutting_recipe",
            POST,
            "post",
            2
        ),
        SuffixedStonecuttingRecipe(
            "post.wooden_stonecutting_recipe",
            POST,
            "post",
            2,
            "_planks"
        ),

        WoodenPlatformBlockModel,
        StonePlatformBlockModel,
        SuffixedBlockState("platform", PLATFORM),
        SuffixedBlockItemModel("platform", PLATFORM),
        SuffixedLootTable("platform", PLATFORM),
        PlatformRecipe,
        SuffixedRecipeAdvancementGenerator(
            "platform.recipe_advancement",
            PLATFORM,
            "platform",
            keyItems = listOf(slabItem)
        ),
        SuffixedStonecuttingRecipe(
            "platform.stonecutting_recipe",
            PLATFORM,
            "platform",
            1
        ),
        SuffixedStonecuttingRecipe(
            "platform.wooden_stonecutting_recipe",
            PLATFORM,
            "platform",
            2,
            "_planks"
        ),

        WoodenStepBlockModel,
        StoneStepBlockModel,
        SuffixedBlockState("step", STEP),
        SuffixedBlockItemModel("step", STEP),
        SuffixedLootTable("step", STEP),
        WoodenStepRecipe,
        StoneStepRecipe,
        SuffixedRecipeAdvancementGenerator(
            "step.recipe_advancement",
            STEP,
            "step",
            keyItems = listOf(slabItem)
        ),
        SuffixedStonecuttingRecipe(
            "step.stonecutting_recipe",
            STEP,
            "step",
            2
        ),
        SuffixedStonecuttingRecipe(
            "step.wooden_stonecutting_recipe",
            STEP,
            "step",
            2,
            "_planks"
        ),

        ShelfBlockModel,
        ShelfBlockState,
        SuffixedBlockItemModel("shelf", SHELF),
        SuffixedLootTable("shelf", SHELF),
        ShelfRecipe,
        SuffixedRecipeAdvancementGenerator(
            "shelf.recipe_advancement",
            SHELF,
            "shelf",
            keyItems = listOf(slabItem)
        ),

        TableLampBlockModel,
        TableLampRecipe,
        SuffixedRecipeAdvancementGenerator(
            "table_lamp.recipe_advancement",
            OTHER,
            "table_lamp",
            keyItems = listOf(carpetItem)
        ),

        SuffixedBlockState("coffee_table", TABLE),
        CoffeeTableBlockModel,
        SuffixedBlockItemModel("coffee_table", TABLE),
        SuffixedLootTable("coffee_table", TABLE),
        CoffeeTableRecipe,
        SuffixedRecipeAdvancementGenerator(
            "coffee_table.recipe_advancement",
            TABLE,
            "coffee_table",
            keyItems = listOf(slabItem)
        ),

        CrateBlockModel,
        SuffixedBlockItemModel("crate", OTHER),
        SuffixedBlockState("crate", OTHER),
        SuffixedLootTable("crate", OTHER),

        TallLampBlockModel,
        TallLampBlockState,
        TallLampItemModel,
        TallLampLootTable,
        TallLampRecipe,
        SuffixedRecipeAdvancementGenerator(
            "tall_lamp.recipe_advancement",
            OTHER,
            "tall_lamp",
            keyItems = listOf(carpetItem)
        )
    )

    object AdornCategory : GeneratorInfo.Category {
        override val id = "categories.adorn"
        override val placeholderTexturePath = null
    }

    enum class Subcategories(override val id: String) : GeneratorInfo.Subcategory {
        Sofas("subcategories.sofas"),
        Chairs("subcategories.chairs"),
        Tables("subcategories.tables"),
        Kitchen("subcategories.kitchen"),
        Drawers("subcategories.drawers"),
        Posts("subcategories.posts"),
        Platforms("subcategories.platforms"),
        Steps("subcategories.steps"),
        Shelves("subcategories.shelves"),
        Other("subcategories.other"),
    }

    @JvmStatic
    fun main(args: Array<String>) {
        io.github.cottonmc.jsonfactory.gui.main(
            arrayOf("--plugin-classes", AdornPlugin::class.qualifiedName!!)
        )
    }
}
