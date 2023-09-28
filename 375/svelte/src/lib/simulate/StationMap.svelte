<script lang="ts">
	import { map } from "../stores";
	import StationSlot from "./StationSlot.svelte";
</script>

<div class="box">
	{#each $map as slot}
		<StationSlot {slot} />
	{/each}
</div>

<style>
	.box {
		width: 500px;
		height: 500px;
	}
</style>
