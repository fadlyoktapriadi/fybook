package com.fyooo.fybook.data.source

import com.fyooo.fybook.data.Book

object BookDataSource {
    val dummyBook = listOf(
        Book(
            id = 1,
            title = "Meditations",
            author = "Marcus Aurelius",
            description = "Meditations is a series of personal writings by the Roman Emperor Marcus Aurelius, in which he outlines his Stoic philosophy and reflections on life.",
            coverUrl = "https://cdn.gramedia.com/uploads/items/Meditations.jpg",
            rating = 4.5f,
            publishedDate = "23-05-2023",
            publisher = "Naura Books",
            isbn = "978-623-242-215-5",
            categories = "Philosophy, Self-Help",
            price = 100000
        ),
        Book(
            id = 2,
            title = "Atomic Habits",
            author = "James Clear",
            description = "Atomic Habits focuses on the power of small habits and incremental changes in achieving personal and professional goals.",
            coverUrl = "https://cdn.gramedia.com/uploads/items/9786020633176_.Atomic_Habit.jpg",
            rating = 4.8f,
            publishedDate = "15-10-2022",
            publisher = "Gramedia Pustaka Utama",
            isbn = "978-602-06-3990-5",
            categories = "Self-Help, Personal Development",
            price = 150000
        ),
        Book(
            id = 3,
            title = "The Power of Now",
            author = "Eckhart Tolle",
            description = "The Power of Now emphasizes the importance of living in the present moment and achieving mindfulness.",
            coverUrl = "https://cdn.gramedia.com/uploads/items/9786230405198.jpeg",
            rating = 4.6f,
            publishedDate = "10-12-2021",
            publisher = "Gramedia Pustaka Utama",
            isbn = "978-602-06-3990-5",
            categories = "Spirituality, Self-Help",
            price = 120000
        ),
        Book(
            id = 4,
            title = "Sapiens: A Brief History of Humankind",
            author = "Yuval Noah Harari",
            description = "Sapiens explores the history of humankind from the Stone Age to the modern era.",
            coverUrl = "https://cdn.gramedia.com/uploads/items/591701404_sapiens.jpg",
            rating = 4.7f,
            publishedDate = "01-01-2015",
            publisher = "Harper",
            isbn = "978-0062316097",
            categories = "History, Anthropology",
            price = 200000
        ),
        Book(
            id = 5,
            title = "Becoming",
            author = "Michelle Obama",
            description = "Becoming is a memoir by Michelle Obama, chronicling her life from childhood to her time as First Lady.",
            coverUrl = "https://m.media-amazon.com/images/I/81cJTmFpG-L._AC_UF1000,1000_QL80_.jpg",
            rating = 4.9f,
            publishedDate = "13-11-2018",
            publisher = "Crown Publishing Group",
            isbn = "978-1524763138",
            categories = "Biography, Memoir",
            price = 250000
        ),
        Book(
            id = 6,
            title = "1984",
            author = "George Orwell",
            description = "1984 is a dystopian novel that explores themes of totalitarianism, surveillance, and individuality.",
            coverUrl = "https://m.media-amazon.com/images/I/71rpa1-kyvL.jpg",
            rating = 4.4f,
            publishedDate = "08-06-1949",
            publisher = "Secker & Warburg",
            isbn = "978-0451524935",
            categories = "Fiction, Dystopian",
            price = 180000
        ),
        Book(
            id = 7,
            title = "To Kill a Mockingbird",
            author = "Harper Lee",
            description = "A novel about racial injustice and moral growth in the American South.",
            coverUrl = "https://inc.mizanstore.com/aassets/img/com_cart/produk/covQN-73.jpg",
            rating = 4.8f,
            publishedDate = "11-07-1960",
            publisher = "J.B. Lippincott & Co.",
            isbn = "978-0061120084",
            categories = "Fiction, Classic",
            price = 220000
        ),
        Book(
            id = 8,
            title = "The Alchemist",
            author = "Paulo Coelho",
            description = "The Alchemist is a philosophical novel about a shepherd's journey to find his personal legend.",
            coverUrl = "https://ebooks.gramedia.com/ebook-covers/29408/big_covers/ID_HCO2015MTH12TALC.jpeg",
            rating = 4.6f,
            publishedDate = "01-01-1988",
            publisher = "HarperOne",
            isbn = "978-0061122415",
            categories = "Fiction, Philosophy",
            price = 130000
        ),
        Book(
            id = 9,
            title = "The Subtle Art of Not Giving a F*ck",
            author = "Mark Manson",
            description = "A self-help book that challenges conventional wisdom about happiness and success.",
            coverUrl = "https://ebooks.gramedia.com/ebook-covers/34319/image_highres/ID_HCO2016MTH09TSAONGAF.jpeg",
            rating = 4.3f,
            publishedDate = "13-09-2016",
            publisher = "HarperOne",
            isbn = "978-0062457714",
            categories = "Self-Help, Psychology",
            price = 140000
        ),
        Book(
            id = 10,
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            description = "A classic novel about teenage rebellion and alienation.",
            coverUrl = "https://npr.brightspotcdn.com/dims4/default/f735808/2147483647/strip/true/crop/363x574+0+0/resize/880x1392!/quality/90/?url=http%3A%2F%2Fnpr-brightspot.s3.amazonaws.com%2Flegacy%2Fsites%2Fwkar%2Ffiles%2Fcatcher_in_the_rye_cover.png",
            rating = 4.2f,
            publishedDate = "16-07-1951",
            publisher = "Little, Brown and Company",
            isbn = "978-0316769488",
            categories = "Fiction, Classic",
            price = 160000
        )
    )
}