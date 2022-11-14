let assignmentList = document.getElementById("article-list")

async function addAssignments() {
    let response = await fetch("./articles.json");
    response = await response.json();
    
    for (const group of response) {
        let articleGroup = document.createElement("article");

        let title = document.createElement("h1");
        let subtitle = document.createElement("div");

        title.textContent = group.name;
        subtitle.textContent = group.desc;
        subtitle.classList.add("desc")
        articleGroup.appendChild(title)
        articleGroup.appendChild(subtitle)

        articleGroup.classList.add("group")
        for (const articleData of group.articles) {
            let article = document.createElement("a");
            let title = document.createElement("div");
            let desc = document.createElement("div");
            
            article.classList.add(articleData.type)
            title.classList.add("title");
            
            title.textContent = articleData.name || "";
            desc.textContent = articleData.desc || "";
            
            if (articleData.date) {
                let date = document.createElement("div");
                date.classList.add("date")
                article.appendChild(date);

                let articleDate = new Date(articleData.date);
        
                let dateString = articleDate.toLocaleString()
        
                date.textContent = dateString;
            }


            article.appendChild(title);
            article.appendChild(desc);
            
            article.href = articleData.path;

            articleGroup.appendChild(article)
        }
        assignmentList.appendChild(articleGroup)
    }
}

addAssignments();