<script lang="ts">
	import Input from "../Input.svelte";
	import {
		sendBuildMessage,
		sendStartMessage,
		sendStopMessage,
	} from "../websocket";
	import Reset from "./Reset.svelte";
	import Solve from "./Solve.svelte";

	export let width = 7;
	export let height = 7;
	export let bombs = 12;

	let solveText: string;
	let idle: boolean = true;

	const resetClick = () => {
		sendBuildMessage(width, height, bombs);
	};
	const solveClick = () => {
		if (!idle) {
			sendStopMessage();
			idle = true;
			solveText = "Go!";
		} else {
			sendStartMessage();
			idle = false;
			solveText = "Stop!";
		}
	};
</script>

<div class="bg-surface1 w-fit m-auto mt-4 p-2 rounded-xl">
	<Input bind:value={width} placeholder="Width" />
	<Input bind:value={height} placeholder="Height" />
	<Input bind:value={bombs} placeholder="Bombs" />
	<div class="flex justify-between mt-3">
		<Reset onclick={resetClick} />
		<Solve bind:text={solveText} bind:enabled={idle} onclick={solveClick} />
	</div>
</div>
