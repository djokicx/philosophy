import wikipedia as w

pages = []

for i in range(0, 10):
	try:
  		page = w.page(w.random())
	except w.exceptions.DisambiguationError as e:
  		page = e.options[0] # take the first option
  	
  	pages.append(page.url)

for i in range(0, 10):
	print pages[i]


