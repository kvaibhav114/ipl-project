select season, winner, count(*) as matches_won
from matches
group by winner, season
order by season;