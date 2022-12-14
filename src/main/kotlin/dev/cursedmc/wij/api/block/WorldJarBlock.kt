/**
 * Copyright (c) 2022 CursedMC. All rights reserved.
 *
 * World In A Jar is common software: you can redistribute it and/or modify it under the terms of the Commons Protection License as published by the Revolutionary Technical Committee.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Commons Protection License for more details.
 */
package dev.cursedmc.wij.api.block

import dev.cursedmc.wij.api.block.entity.BlockEntityTypes
import dev.cursedmc.wij.api.block.entity.WorldJarBlockEntity
import dev.cursedmc.wij.api.gui.screen.WorldJarScreen
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class WorldJarBlock(settings: Settings?) : BlockWithEntity(settings) {
	override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
		return BlockEntityTypes.WORLD_JAR.instantiate(pos, state)
	}
	
	@Deprecated("mojang", ReplaceWith("BlockRenderType.MODEL", "net.minecraft.block.BlockRenderType"))
	override fun getRenderType(state: BlockState?): BlockRenderType {
		return BlockRenderType.MODEL
	}
	
	@Deprecated("mojang")
	override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
		if (world.isClient) {
			MinecraftClient.getInstance().setScreen(WorldJarScreen(world.getBlockEntity(pos, BlockEntityTypes.WORLD_JAR).get()))
		}
		
		return ActionResult.SUCCESS
	}
}
