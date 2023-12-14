<script lang="ts">
	import Grid from "./lib/simulate/Grid.svelte";
	import Settings from "./lib/simulate/Settings.svelte";
	import Stats from "./lib/simulate/Stats.svelte";
</script>

<main>
	<h1 class="text-sky text-4xl text-center m-5 font-bold">Minesweeper</h1>
	<Stats />
	<Grid />
	<Settings />
</main>

<style>
	h1 {
		text-shadow: 0px 0px 30px rgba(var(--ctp-sky));
	}
</style>
