<script lang="ts">
	import Input from "../Input.svelte";
	import {
		sendBuildMessage,
		sendStartMessage,
		sendStopMessage,
	} from "../websocket";
	import Go from "./Go.svelte";
	import Grid from "./Grid.svelte";
	import Reset from "./Reset.svelte";

	export let visible: boolean;
	let enabled: boolean = true;
	let text = "Go!";

	let size: number = 7;
	let types: number = 4;
	let threads: number = 4;

	const goClick = () => {
		if (enabled) {
			sendStartMessage(threads);
			enabled = false;
			text = "Stop!";
		} else {
			enabled = true;
			sendStopMessage();
			text = "Go!";
		}
	};

	const resetClick = () => {
		sendStopMessage();
		text = "Go!";
		enabled = true;

		sendBuildMessage(size, types);
	};
</script>

{#if visible}
	<Grid />
	<div class="flex gap-2 justify-center mt-4">
		<Reset onclick={resetClick} />
		<Go {enabled} {text} onclick={goClick} />
	</div>
	<div
		class="flex flex-col items-center bg-surface1 w-fit m-auto p-2 rounded-xl mt-4"
	>
		<Input bind:value={size} placeholder="Size of grid" />
		<Input bind:value={types} placeholder="Types of stations" />
	</div>
	<div
		class="flex flex-col items-center bg-surface1 w-fit m-auto p-2 rounded-xl mt-4"
	>
		<Input bind:value={threads} placeholder="Threads" />
	</div>
{/if}
