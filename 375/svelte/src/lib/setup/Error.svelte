<script lang="ts">
	import { fly } from "svelte/transition";
	export let message = "An unexpected error has occured.";
</script>

<div
	transition:fly={{ y: 20 }}
	class=" p-2 text-text w-96 min-h-20 relative m-2 border-l-4 bg-surface2 rounded-lg border-red"
>
	<h2 class="font-extrabold text-xl">ERROR</h2>
	<div class="text">
		{message}
	</div>
</div>
