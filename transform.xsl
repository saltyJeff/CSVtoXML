<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>Unit types</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>Names</th>
      <th>Class</th>
	  <th>Attack</th>
	  <th>Damage</th>
	  <th>Rate of Fire</th>
	  <th>Speed</th>
	  <th>Story</th>
    </tr>
    <xsl:for-each select="root/element">
    <tr>
      <td><xsl:value-of select="name"/></td>
      <td><xsl:value-of select="class"/></td>
	  <td><xsl:value-of select="attack"/></td>
	  <td><xsl:value-of select="damage"/></td>
	  <td><xsl:value-of select="rateoffire"/></td>
	  <td><xsl:value-of select="speed"/></td>
	  <td><xsl:value-of select="bio"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>