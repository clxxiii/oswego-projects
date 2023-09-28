<script lang="ts">
	import Setup from "./lib/setup/Setup.svelte";
	import Simulate from "./lib/simulate/Simulate.svelte";

	let setupPhase: boolean = false;
</script>

<main>
	<Setup bind:visible={setupPhase} />
	<Simulate visible={!setupPhase} />
</main>
