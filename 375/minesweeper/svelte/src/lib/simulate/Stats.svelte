<script lang="ts">
	import { Flag, Percent } from "lucide-svelte";
	import { grid } from "../stores";
</script>

<div
	class:solved={$grid.solved}
	class:failed={$grid.failed}
	class="w-64 p-2 m-auto bg-surface1 text-text font-bold rounded flex justify-between transition"
>
	<div class="flex gap-2">
		<Flag width={20} height={20} />
		{$grid.bombs - $grid.flags}
	</div>
	<div class="flex gap-2">
		<Percent width={20} height={20} />
		{Math.round($grid.progress * 10000) / 100}
	</div>
</div>

<style>
	div.solved {
		color: rgba(var(--ctp-base));
		background-color: rgba(var(--ctp-green));
	}
	div.failed {
		color: rgba(var(--ctp-base));
		background-color: rgba(var(--ctp-red));
	}
</style>
