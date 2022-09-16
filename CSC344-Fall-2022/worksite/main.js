let assignmentList = document.getElementById("article-list");
import * as articles from "./articles.json";
import "./style.css";

async function addAssignments() {
	for (const articleData of articles.default) {
		let article = document.createElement("a");
		let title = document.createElement("div");
		let desc = document.createElement("div");

		article.classList.add(articleData.type);
		title.classList.add("title");
		desc.classList.add("desc");

		title.textContent = articleData.name || "";
		desc.textContent = articleData.desc || "";

		if (articleData.date) {
			let date = document.createElement("div");
			date.classList.add("date");
			article.appendChild(date);

			let articleDate = new Date(articleData.date);

			let dateString = articleDate.toLocaleString();

			date.textContent = dateString;
		}

		article.appendChild(title);
		article.appendChild(desc);

		article.href = `https://www.cs.oswego.edu/~efereira/CS344/articles/${articleData.filename}`;

		assignmentList.appendChild(article);
	}
}

addAssignments();
