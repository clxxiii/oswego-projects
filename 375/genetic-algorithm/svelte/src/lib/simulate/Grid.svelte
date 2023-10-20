<script lang="ts">
	import { grid } from "../stores";
	import Cell from "./Cell.svelte";
</script>

<table class="overflow-hidden rounded-xl m-auto mt-5">
	{#each $grid as row}
		<tr class="row">
			{#each row as type}
				<Cell {type} />
			{/each}
		</tr>
	{/each}
</table>
