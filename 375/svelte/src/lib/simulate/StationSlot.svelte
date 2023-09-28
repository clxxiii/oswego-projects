<script lang="ts">
	import type { StationSlot } from "../../app";

	export let slot: StationSlot;
</script>

<div class="slot" style="--x: {slot.x * 10}px; --y: {slot.y * 10}px" />

<style>
	.slot {
		position: absolute;
		top: 50%;
		left: 50%;
		background: white;
		border-radius: 20px;
		border: solid rgb(var(--ctp-base)) 5px;
		width: 20px;
		height: 20px;
		transform: translate(var(--x), var(--y));
	}
</style>
