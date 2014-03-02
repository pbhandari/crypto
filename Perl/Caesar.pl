use strict;
use warnings;

package Caesar;
sub new
{
    my $class = shift;
    my $self = {key = shift, alphabet = shift};
    return $self;
}
