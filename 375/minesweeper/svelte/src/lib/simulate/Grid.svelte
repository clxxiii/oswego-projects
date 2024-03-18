<script lang="ts">
	import { grid } from "../stores";
	import Cell from "./Cell.svelte";
</script>

<table class="m-auto mt-5">
	{#key $grid}
		{#if $grid?.grid}
			{#each $grid.grid as row}
				<tr class="row">
					{#each row as data}
						<Cell {data} />
					{/each}
				</tr>
			{/each}
		{/if}
	{/key}
</table>
