<script lang="ts">
	import { grid } from "../stores";
	import Cell from "./Cell.svelte";
</script>

<table class="m-auto mt-5">
	{#each $grid as row}
		<tr class="row">
			{#each row as data}
        {#key data}
				  <Cell {data} />
        {/key}
			{/each}
		</tr>
	{/each}
</table>
