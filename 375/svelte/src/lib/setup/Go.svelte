<script lang="ts">
	export let enabled = false;
	export let onclick: () => any;
</script>

<button
	on:click={onclick}
	class=" w-24 font-bold text-md transition bg-mantle text-surface2 rounded-xl p-3"
	class:enabled
>
	Go!
</button>

<style>
	button.enabled {
		background-color: rgba(var(--ctp-green));
		color: rgba(var(--ctp-base));
	}
</style>
