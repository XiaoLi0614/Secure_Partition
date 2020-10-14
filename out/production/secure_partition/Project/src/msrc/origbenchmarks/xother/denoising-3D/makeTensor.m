function [T T_posDef scaleFactor] = makeTensor(S,gradient,scale)

r=gradient';

b=1;

% finding tensor
R = [
        r(1,:).^2 
        2 * r(1,:) .* r(2,:)
        2 * r(1,:) .* r(3,:)
        r(2,:).^2 
        2 * r(2,:) .* r(3,:)
        r(3,:).^2 
]';


S = S+1; %% to avoid log of zero

for row = 1:size( S,1 )
    for col = 1:size( S,2)
        for lvl = 1:size( S,3)
            s = double( squeeze( S( row,col,lvl,: )));           
            d = log( s(1) ./ s(2:end) );
            x(row,col,lvl,:) =  (b*R) \ d;
        end
    end
end

[x2 scaleFactor] = makePosDef(x,scale);

T_posDef = convertData3d(x2);

T = convertData3d(x*scaleFactor);