input = [
        [ file: "build/html5/index.html", ancestorId: 381255740, preambleTitle: "Macro architecture"]
]

confluenceAPI = 'https://devcolla.swisslog.com/rest/api/'
confluenceSpaceKey = 'MSA'
confluenceCreateSubpages = true
confluencePagePrefix = 'MD: '
confluenceCredentials = new File('/opt/secret').text.bytes.encodeBase64().toString()
extraPageContent = '<ac:structured-macro ac:name="warning"><ac:parameter ac:name="title" /><ac:rich-text-body>This is a generated page, do not edit!</ac:rich-text-body></ac:structured-macro>'