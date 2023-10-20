<script lang="ts">
	import Setup from "./lib/setup/Setup.svelte";
	import Simulate from "./lib/simulate/Simulate.svelte";

	let setupPhase: boolean = false;
	let gridShown: boolean;

	$: gridShown = !setupPhase;
</script>

<main>
	<h1 class="text-sky text-4xl text-center m-5 font-bold">Station Simulator</h1>
	<Setup bind:visible={setupPhase} />
	<Simulate bind:visible={gridShown} />
</main>

<style>
	h1 {
		text-shadow: 0px 0px 30px rgba(var(--ctp-sky));
	}
</style>
