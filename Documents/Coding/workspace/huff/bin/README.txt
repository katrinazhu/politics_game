Name: Katrina Zhu
Net ID: kz37
Hours Spent: around 14
Consulted With: Efe Aras helped me on Piazza
Resources Used: none
Analysis:
When I compressed the folder calgary, these were the results:
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/bib.hf
bib from	 111261 to	 73791 in	 0.193
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/book1.hf
book1 from	 768771 to	 445616 in	 0.813
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/book2.hf
book2 from	 610856 to	 373660 in	 0.656
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/geo.hf
geo from	 102400 to	 122064 in	 0.261
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/news.hf
news from	 377109 to	 253728 in	 0.476
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/obj1.hf
obj1 from	 21504 to	 26893 in	 0.053
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/obj2.hf
obj2 from	 246814 to	 267352 in	 0.505
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/paper1.hf
paper1 from	 53161 to	 35931 in	 0.066
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/paper2.hf
paper2 from	 82199 to	 50581 in	 0.100
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/paper3.hf
paper3 from	 46526 to	 29367 in	 0.056
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/paper4.hf
paper4 from	 13286 to	 9178 in	 0.025
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/paper5.hf
paper5 from	 11954 to	 8796 in	 0.018
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/paper6.hf
paper6 from	 38105 to	 26433 in	 0.066
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/pic.hf
pic from	 513216 to	 249135 in	 0.451
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/progc.hf
progc from	 39611 to	 30662 in	 0.088
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/progl.hf
progl from	 71646 to	 52809 in	 0.102
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/progp.hf
progp from	 49379 to	 35676 in	 0.072
compressing to: /Users/katzhu/Documents/workspace/huff/data/calgary/trans.hf
trans from	 93695 to	 77277 in	 0.159
--------
total bytes read: 4131525
total compressed bytes 2685322
total percent compression 35.004
compression time: 5.124

When I compressed the folder waterloo, these were the results:
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/clegg.tif.hf
clegg.tif from	 2149096 to	 2034591 in	 3.623
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/frymire.tif.hf
frymire.tif from	 3706306 to	 2286667 in	 4.160
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/lena.tif.hf
lena.tif from	 786568 to	 855562 in	 1.633
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/monarch.tif.hf
monarch.tif from	 1179784 to	 1217764 in	 2.382
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/peppers.tif.hf
peppers.tif from	 786568 to	 811963 in	 1.542
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/sail.tif.hf
sail.tif from	 1179784 to	 1174394 in	 2.235
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/serrano.tif.hf
serrano.tif from	 1498414 to	 1233081 in	 2.236
compressing to: /Users/katzhu/Documents/workspace/huff/data/waterloo/tulips.tif.hf
tulips.tif from	 1179784 to	 1227715 in	 2.453
--------
total bytes read: 12466304
total compressed bytes 10841737
total percent compression 13.032
compression time: 20.264

I found three interesting trends when looking at these compressed files:
1) Sometimes, the compressed file was larger than the original.
The compressed file was never extraordinarily larger than the original, but it was
larger nonetheless sometimes.  I found that this only happened with the smaller files, 
such as the 21,504 bit file in calgary that compressed to 26,893 bits; or the
786,568 bit file in waterloo that compressed to 855,562.  I attribute this to the added
header information in the compressed file.  The header added extra bits to the compressed
file that the original file did not have.  For a large enough file, these extra bits
were insignificant, but for a smaller compressed file, this could be enough to make the 
compressed file larger than the original.
2) It took a lot longer to compress picture files than text files.
For files of about the same size, the picture file took about twice as long to compress
as the text file did.  For example, it took 0.813 seconds for a 768,771 bit text file to 
compress, but 1.633 seconds for a 786,568 bit picture file.  I'm not really sure why this
is, but I am assuming this points to the fact that picture file has more values that just
letters and limited characters.  The picture file probably makes fuller use of all 256
possible encodings, meaning that it has to create a bigger tree and make more codes, which
on the whole takes more time. 
3) Picture files did not compress as much as text files.
On the whole, text files had 35.004% compression and picture files had 13.032% compression.
This could be attributed to the same reason as above: for some reason (maybe because
there are a greater variety of colors than there are words), picture files store more values.  
When they have more values stored, we need to create a bigger tree, and a bigger tree
corresponds to more bits per value.  Thus, when compressing the file, a compressed
picture file will have more bits than a compressed text file. So, it makes sense that its
percentage of compression would be lower.

Impressions: I really liked this assignment.  I thought it was not only a good exercise
in coding and making trees, but we also had good guidance through all the resources
we were given.  Furthermore, it was cool that we could compress actual files and uncompress
them to be the same file again.  However, I do wish the instructions were a little more
organized.