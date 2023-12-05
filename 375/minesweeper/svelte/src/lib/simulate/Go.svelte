<script lang="ts">
	export let enabled = false;
	export let text = "Go!";
	export let onclick: () => any;
</script>

<button
	on:click={onclick}
	class="block w-24 font-bold text-md transition bg-red text-surface2 rounded-xl p-3"
	class:enabled
>
	{text}
</button>

<style>
	button.enabled {
		background-color: rgba(var(--ctp-green));
	}
</style>
