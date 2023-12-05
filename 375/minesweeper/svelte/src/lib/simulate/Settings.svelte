<script lang="ts">
	import Input from "../Input.svelte";
	import { sendBuildMessage } from "../websocket";
	import Reset from "./Reset.svelte";

	export let width = 7;
	export let height = 7;
	export let bombs = 12;

	const resetClick = () => {
		sendBuildMessage(width, height, bombs);
	};
</script>

<div class="bg-surface1 w-fit m-auto mt-4 p-2 rounded-xl">
	<Input bind:value={width} placeholder="Width"></Input>
	<Input bind:value={height} placeholder="Height"></Input>
	<Input bind:value={bombs} placeholder="Bombs"></Input>
	<Reset onclick={resetClick}></Reset>
</div>
