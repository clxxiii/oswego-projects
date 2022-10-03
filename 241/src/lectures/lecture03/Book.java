package lectures.lecture03;

interface Book {
    String publisher = "SUNY Oswego Press";

    void showPublisher();
    void showAuthor();
    void showMaxPage();
}

class Textbook implements Book {
    String author;
    int maxPage;

    Textbook (String author) {
        this.author = author;
    }

    // implementation of abstract methods in interface
    public void showPublisher() {
        System.out.println("Book published by " + publisher);
    }

    public void showAuthor() {
        System.out.println("Book written by " + author);
    }

    public void showMaxPage(){
        System.out.println("Book has the max page of " + maxPage);
    }

    // methods for the Textbook class
    public void addMaxPage(int maxPage){
        this.maxPage = maxPage;
    }
}
