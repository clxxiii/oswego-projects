<script lang="ts">
	export let type: number;

	const colors: { [key: number]: string } = {
		[-1]: "var(--ctp-base)",
		0: "var(--ctp-red)",
		1: "var(--ctp-blue)",
		2: "var(--ctp-green)",
		3: "var(--ctp-peach)",
		4: "var(--ctp-yellow)",
		5: "var(--ctp-mauve)",
		6: "var(--ctp-sky)",
		7: "var(--ctp-flamingo)",
		8: "var(--ctp-pink)",
		9: "var(--ctp-rosewater)",
	};
</script>

<td class="w-12 h-12" style="--color: {colors[type]}" />

<style>
	td {
		background-color: rgba(var(--color));
	}
</style>
