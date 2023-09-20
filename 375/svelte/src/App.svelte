<script lang="ts">
	import Setup from "./lib/setup/Setup.svelte";
	import Simulate from "./lib/simulate/Simulate.svelte";

	let idle: boolean;
</script>

<main>
	<Setup bind:visible={idle} />
	<Simulate visible={!idle} />
</main>
