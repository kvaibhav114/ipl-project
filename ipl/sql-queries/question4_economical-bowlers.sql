select d.bowler, sum(d.total_runs) / (count(*) / 6.0) as eco
from deliveries d
join matches m on d.match_id = m.id
where m.season = 2015
group by d.bowler
order by eco asc
limit 10;