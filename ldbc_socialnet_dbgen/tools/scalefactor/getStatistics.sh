RCOMMAND=/home/aprat/R-3.1.0/bin/R

#May need to install additional package such as data.table, igraph, bit64
#install.packages("data.table")

SCALEFACTOR=1
DATAOUTPUTDIR=/data/aprat/datasets/LDBC/$SCALEFACTOR.static/social_network

echo "\\hline    \\multicolumn{5}{|c|}{SF = $SCALEFACTOR }  \\\\"
echo "Network cluster coefficient"							       
$RCOMMAND --slave -f transitivity.R --args $DATAOUTPUTDIR/person_knows_person_*.csv 

echo "\\hline & Min & Max & Mean & Median   \\\\"

echo "Number of comments per users"
$RCOMMAND --slave -f minmaxmean_comment.R --args $DATAOUTPUTDIR/comment_hasCreator_person_*.csv 

echo "Number of posts per users"
$RCOMMAND --slave -f minmaxmean_post.R --args $DATAOUTPUTDIR/post_hasCreator_person_*.csv 

echo "Number of friends per users"
$RCOMMAND --slave -f minmaxmean_friendships.R --args $DATAOUTPUTDIR/person_knows_person_*.csv 

echo "Number of likes per users"
$RCOMMAND --slave -f minmaxmean_likes.R --args $DATAOUTPUTDIR/*likes*.csv 


