package at.playground.datepicker

import android.util.Log

object DataSource {

    fun getRandomLetters(amount: Int = 5): List<Char> = letters.shuffled().take(amount)

    // fun getRandomLetters(amount: Int = 5): List<Char> = letters.take(amount)
    fun getRandomLetters2(index: Int = 0, prefix: String = "", amount: Int = 5): List<Char> {
        val result = dictionary
            .filter { it.startsWith(prefix, true) }
            .mapNotNull { it.getOrNull(index)?.uppercaseChar() }
            .distinct()
            .shuffled()
            .take(amount).also {
                Log.d("blah", "result(${it.size}): $it")
            }

        return if (result.size < amount) {
            val additional = letters
                .filter { it !in result }
                .shuffled()
                .take(amount - result.size).also {
                    Log.d("blah", "return if(${it.size}): $it")
                }

            (result + additional).shuffled()
        } else {
            result
        }
    }

    fun getRandomLetters3(index: Int = 0, seeds: List<Char> = emptyList(), amount: Int = 5): List<Char> {
        val result = dictionary

            // .filter { it.startsWith(prefix, true) }
            // .mapNotNull { it.getOrNull(index)?.uppercaseChar() }
            // .distinct()
            // .shuffled()
            // .take(amount).also {
            //     Log.d("blah", "result(${it.size}): $it")
            // }

        return emptyList()
    }

    fun getPossibleAnswers(prefix: String): List<String> = dictionary.filter {
        it.startsWith(prefix, true)
    }

    private val letters: List<Char> = ('A').rangeTo('Z').toList()
    private val dictionary: List<String> = listOf(
        "animal",
        "auto",
        "anecdote",
        "alphabet",
        "all",
        "awesome",
        "arise",
        "balloon",
        "basket",
        "bench",
        "best",
        "birthday",
        "book",
        "briefcase",
        "camera",
        "camping",
        "candle",
        "cat",
        "cauliflower",
        "chat",
        "children",
        "class",
        "classic",
        "classroom",
        "coffee",
        "colorful",
        "cookie",
        "creative",
        "cruise",
        "dance",
        "daytime",
        "dinosaur",
        "doorknob",
        "dine",
        "dream",
        "dusk",
        "eating",
        "elephant",
        "emerald",
        "eerie",
        "electric",
        "finish",
        "flowers",
        "follow",
        "fox",
        "frame",
        "free",
        "frequent",
        "funnel",
        "green",
        "guitar",
        "grocery",
        "glass",
        "great",
        "giggle",
        "haircut",
        "half",
        "homemade",
        "happen",
        "honey",
        "hurry",
        "hundred",
        "ice",
        "igloo",
        "invest",
        "invite",
        "icon",
        "introduce",
        "joke",
        "jovial",
        "journal",
        "jump",
        "join",
        "kangaroo",
        "keyboard",
        "kitchen",
        "koala",
        "kind",
        "kaleidoscope",
        "landscape",
        "late",
        "laugh",
        "learning",
        "lemon",
        "letter",
        "lily",
        "magazine",
        "marine",
        "marshmallow",
        "maze",
        "meditate",
        "melody",
        "minute",
        "monument",
        "moon",
        "motorcycle",
        "mountain",
        "music",
        "north",
        "nose",
        "night",
        "name",
        "never",
        "negotiate",
        "number",
        "opposite",
        "octopus",
        "oak",
        "order",
        "open",
        "polar",
        "pack",
        "painting",
        "person",
        "picnic",
        "pillow",
        "pizza",
        "podcast",
        "presentation",
        "puppy",
        "puzzle",
        "recipe",
        "release",
        "restaurant",
        "revolve",
        "rewind",
        "room",
        "run",
        "secret",
        "seed",
        "ship",
        "shirt",
        "should",
        "small",
        "spaceship",
        "stargazing",
        "skill",
        "street",
        "style",
        "sunrise",
        "taxi",
        "tidy",
        "timer",
        "together",
        "tooth",
        "tourist",
        "travel",
        "truck",
        "under",
        "useful",
        "unicorn",
        "unique",
        "uplift",
        "uniform",
        "vase",
        "violin",
        "visitor",
        "vision",
        "volume",
        "view",
        "walrus",
        "wander",
        "world",
        "winter",
        "well",
        "whirlwind",
        "x-ray",
        "xylophone",
        "yoga",
        "yogurt",
        "yoyo",
        "you",
        "year",
        "yummy",
        "zebra",
        "zigzag",
        "zoology",
        "zone",
        "zeal",
    )
}
