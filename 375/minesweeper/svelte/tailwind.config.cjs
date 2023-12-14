/** @type {import('tailwindcss').Config}*/
module.exports = {
  plugins: [
    require("@catppuccin/tailwindcss")({
      prefix: false,
      defaultFlavour: "mocha",
    }),
  ],
  theme: {
    extend: {},
  },
  content: ["./index.html", "./src/**/*.{svelte,js,ts}"], //for unused css
};
