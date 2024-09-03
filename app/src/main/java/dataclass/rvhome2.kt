package dataclass

data class rvhome2 (
    val id : String,
    val category1 : String,
    val category2 : String,
    val category3 : String,
    val jobstatus :String,
    val content : String,
    val write_date:String

)


    data class rvhome2s (

        val rvhome2: List<rvhome2>


)