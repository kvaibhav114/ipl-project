select season, count(*) as matches
from matches
group by season
order by season;