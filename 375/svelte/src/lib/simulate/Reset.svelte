<script lang="ts">
	export let onclick: () => any;
</script>

<button
	on:click={onclick}
	class="block bg-blue w-24 font-bold text-md transition text-surface2 rounded-xl p-3"
>
	Reset
</button>
