<script lang="ts">
	import Error from "./Error.svelte";
	import Input from "./Input.svelte";
	import Go from "./Go.svelte";
	import { fly } from "svelte/transition";
	import { socket } from "../websocket";

	let stations = 48;
	let spots = 48;

	let enabled: boolean;
	$: enabled =
		stations != 0 && spots != 0 && stations <= spots && !disconnected;

	let disconnected = false;
	socket.addEventListener("error", () => {
		disconnected = true;
	});

	socket.addEventListener("open", () => {
		disconnected = false;
	});

	export let visible = true;

	const onclick = () => {
		console.log("hi!");
	};
</script>

{#if visible}
	<div
		class="absolute overflow-hidden bottom-2 right-2 flex items-center flex-col"
		transition:fly={{ y: -100 }}
	>
		{#if disconnected}
			<Error message="Cannot connect to the websocket!" />
		{/if}
		{#if stations > spots}
			<Error message="Number of stations must be higher than number of slots" />
		{/if}
	</div>
	<h1 class="text-sky text-4xl text-center m-5 font-bold">Station Simulator</h1>
	<div class="flex flex-col items-center">
		<Input bind:value={stations} placeholder="Number of Stations" />
		<Input bind:value={spots} placeholder="Number of slots" />
		<Go {onclick} bind:enabled />
	</div>
{/if}

<style>
	h1 {
		text-shadow: 0px 0px 30px rgba(var(--ctp-sky));
	}
</style>
